package com.yh.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.yh.entity.Page;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

/**
 * ************************************
 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。
 *
 * @param <T>
 * @param <PK> ************************************
 */
@SuppressWarnings("all")
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

    private String databaseType;//数据库类型，不同的数据库有不同的分页方法
    private String printSql;//控制台输出SQL语句，0不输出，1输出
    private String writeSqlLog;//保存sql操作日志

    /**
     * ************************************
     * 拦截后要执行的方法
     *
     * @param <T>
     * @param <PK> ************************************
     */
    public Object intercept(Invocation invocation) throws Throwable {
        //对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，
        //BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，
        //SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是
        //处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个
        //StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、
        //PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。
        //我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候
        //是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        //通过反射获取到当前RoutingStatementHandler对象的delegate属性
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
        //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
        BoundSql bounSql = delegate.getBoundSql();
        //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
        //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
        String sql = bounSql.getSql();
        if ("1".equals("1")) {
            System.out.println("----------------------------------------------SQL Print----------------------------------------------");
            String beSql = formatSql(sql);
            System.out.println(beSql);
            System.out.println("-----------------------------------------------------------------------------------------------------");
        }
        Object obj = bounSql.getParameterObject();
        //通过传入的是Page对象 认定它是需要进行分页操作的
        if (obj instanceof Page) {
            Page page = (Page)obj;
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            //拦截到的prepare方法参数是一个Connection对象
            Connection connection = (Connection) invocation.getArgs()[0];
            //如果是第一次，那么就统计，接下来翻页将不统计
            if (page.getPageNo() == 1) {
                this.setTotalRecord(page, mappedStatement, connection);
            }
            //获取分页SQL语句
            String pageSql = this.getPageSql(page, sql);
            //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
            ReflectUtil.setFieldValue(bounSql, "sql", pageSql);
        }

        return invocation.proceed();
    }

    /**
     * ************************************
     * 拦截器对应的封装原始对象的方法
     *
     * @param <T>
     * @param <PK> ************************************
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * ************************************
     * 设置注册拦截器时设定的属性
     *
     * @param <T>
     * @param <PK> ************************************
     */
    public void setProperties(Properties properties) {
        this.databaseType = properties.getProperty("databaseType");
        this.printSql = properties.getProperty("printSql");
        this.writeSqlLog = properties.getProperty("writeSqlLog");
    }

    /**
     * ************************************
     * 给当前的参数对象page设置总记录数
     *
     * @param page            Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection      当前的数据库连接
     *                        ************************************
     */
    private void setTotalRecord(Page page, MappedStatement mappedStatement, Connection connection) {
        //获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
        //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        //获取到我们自己写在Mapper映射语句中对应的Sql语句
        String sql = boundSql.getSql();
        //通过查询Sql语句获取到对应的计算总记录数的sql语句
        String countSql = this.getCountSql(sql);
        //通过BoundSql获取对应的参数映射
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
        //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        //通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            //通过parameterHandler给PreparedStatement对象设置参数
            parameterHandler.setParameters(pstmt);
            //之后就是执行获取总记录数的Sql语句和获取结果了
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                //给当前的参数page对象设置总记录数
                page.setTotalRecord(totalRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ************************************
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     *
     * @param sql ************************************
     */
    private String getCountSql(String sql) {
        String new_sql = "select count(*) from (" + sql + ")";
        return new_sql;
    }

    private String getPageSql(Page page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if ("mysql".equalsIgnoreCase(databaseType)) {
            return getMySqlPageSql(page, sqlBuffer);
        } else if ("oracle".equalsIgnoreCase(databaseType)) {
            return getOraclePageSql(page, sqlBuffer);
        }
        return sqlBuffer.toString();
    }

    /**
     * ************************************
     * 获取Mysql数据库的分页查询语句
     *
     * @param sql ************************************
     */
    private String getMySqlPageSql(Page page, StringBuffer sqlBuffer) {
        if (page.getOrderField() != null && !page.getOrderField().equals("") && sqlBuffer.indexOf("order by") == -1) {
            sqlBuffer.append(" order by ").append(page.getOrderField()).append(" ").append(page.getOrderDirection());
        }
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
        return sqlBuffer.toString();
    }

    private String getOraclePageSql(Page page, StringBuffer sqlBuffer) {
        if (page.getOrderField() != null && !page.getOrderField().equals("") && sqlBuffer.indexOf("order by") == -1) {
            sqlBuffer.append(" order by ").append(page.getOrderField()).append(" ").append(page.getOrderDirection());
        }
        //计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
        int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
        sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
        sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
        System.out.println(sqlBuffer.toString());
        return sqlBuffer.toString();
    }

    /**
     * ************************************
     * 格式化SQL
     *
     * @param sql ************************************
     */
    private String formatSql(String sql) {
        // 输入sql字符串空判断
        if (sql == null || sql.length() == 0) {
            return "";
        }
        // 美化sql
        sql = beautifySql(sql);
        return sql;
    }

    /**
     * ************************************
     * 美化SQL
     *
     * @param sql ************************************
     */
    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }

}
