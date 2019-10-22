package com.yh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yh.dao.BaseDao;
import com.yh.entity.Page;
/**
 * ************************************
 * 基础Dao
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
@Repository
public class BaseDaoImpl<T,PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T, PK> {
	public static final String SQLNAME_SEPARATOR=".";
	public static final String SQL_INSERT="insert";
	public static final String SQL_UPDATE="update";
	public static final String SQL_DELETE="delete";
	public static final String SQL_GETPAGELIST="getPageList";
	public static final String SQL_GETLIST="getList";
	public static final String SQL_GETONE="getOne";
	private String sqlNameSpace=this.getNameSpace();
	
	public String getSqlNameSpace(){
		return sqlNameSpace;
	}
	
	public void setSqlNameSpace(String sqlNameSpace){
		this.sqlNameSpace=sqlNameSpace;
	}
	
	public String getNameSpace(){
		Class<T> clazz=null;
		Class c=getClass();
		System.out.println("nameSpace----"+c.toString()+"----"+this.getClass().toString());
		Type type=c.getGenericSuperclass();
		System.out.println("type------"+type);
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType=((ParameterizedType)type).getActualTypeArguments();
			clazz=(Class<T>) parameterizedType[0];
			System.out.println("第二个参数类型------"+(Class<T>) parameterizedType[1]);
			return clazz.getSimpleName();
		}
		return null;
	}
	
	protected String getSqlName(String sqlName){
		if (sqlName.contains(".")) {
			return sqlName;
		}else{
			System.out.println(sqlNameSpace+SQLNAME_SEPARATOR+sqlName);
			return sqlNameSpace+SQLNAME_SEPARATOR+sqlName;
		}
	}

	/**
	 * ************************************
	 * 添加
	 * @param obj
	 * @return int 受影响行数或 主键ID
	 * ************************************
	 */
	public int insert(T obj){
		return this.getSqlSession().insert(getSqlName(SQL_INSERT),obj);
	}
	/**
	 * ************************************
	 * 添加-指定SQL语句传入参数添加
	 * @param statement
	 * @param parameter
	 * @return int 受影响行数或 主键ID
	 * ************************************
	 */
	public int insert(String statement,Object parameter){
		return this.getSqlSession().insert(getSqlName(statement), parameter);
	}
	/**
	 * ************************************
	 * 添加-指定SQL语句添加(无参数)
	 * @param statement
	 * @return int 受影响行数或 主键ID
	 * ************************************
	 */
	public int insert(String statement){
		return this.getSqlSession().insert(getSqlName(statement));
	}
	
	/**
	 * ************************************
	 * 修改
	 * @param obj
	 * @return int
	 * ************************************
	 */
	public int update(T obj){
		return this.getSqlSession().update(getSqlName(SQL_UPDATE), obj);
	}
	/**
	 * ************************************
	 * 修改-指定SQL语句，传入参数修改数据
	 * @param obj
	 * @return int
	 * ************************************
	 */
	public int update(String statement,Object parameter){
		return this.getSqlSession().update(getSqlName(statement), parameter);
	}
	/**
	 * ************************************
	 * 修改-指定SQL语句修改数据(无参数)
	 * @param obj
	 * @return int
	 * ************************************
	 */
	public int update(String statement){
		return this.getSqlSession().update(getSqlName(statement));
	}
	
	/**
	 * ************************************
	 * 删除
	 * @param 
	 * ************************************
	 */
	public int delete(T obj){
		return this.getSqlSession().delete(getSqlName(SQL_DELETE),obj);
	}
	/**
	 * ************************************
	 * 删除-指定SQL语句传参数删除
	 * @param 
	 * ************************************
	 */
	public int delete(String statement,Object parameter){
		return this.getSqlSession().delete(getSqlName(statement),parameter);
	}
	/**
	 * ************************************
	 * 删除-指定SQL语句删除(无参数)
	 * @param 
	 * ************************************
	 */
	public int delete(String statement){
		return this.getSqlSession().delete(getSqlName(statement));
	}
	
	/**
	 * ************************************
	 * 查询集合
	 * @param 
	 * ************************************
	 */
	public void getPageList(Page p){
		p.setResults(this.getSqlSession().selectList(getSqlName(SQL_GETPAGELIST),p));
	}
	public void getPageList(String statement,Page p){
		p.setResults(this.getSqlSession().selectList(getSqlName(statement), p));
	}
	public List<Map<String,Object>> getList(Object parameter){
		return this.getSqlSession().selectList(getSqlName(SQL_GETLIST), parameter);
	}
	public List<Map<String,Object>> getList(String statement,Object parameter){
		return this.getSqlSession().selectList(getSqlName(statement), parameter);
	}
	public List getObjectList(String statement){
		return this.getSqlSession().selectList(getSqlName(statement));
	}
	public List getObjectList(String statement,Object parameter){
		return this.getSqlSession().selectList(getSqlName(statement),parameter);
	}
	/**
	 * ************************************
	 * 查询单个记录
	 * @param 
	 * ************************************
	 */
	public T getOne(Object parameter){
		return (T) this.getSqlSession().selectOne(getSqlName(SQL_GETONE), parameter);
	}
	public T getOne(String statement,Object parameter){
		return (T) this.getSqlSession().selectOne(getSqlName(statement), parameter);
	}
	public Object selectOne(String statement,Object parameter){
		return this.getSqlSession().selectOne(getSqlName(statement), parameter);
	}
	public Integer getInt(String statement,Object parameter){
		return (Integer) this.getSqlSession().selectOne(getSqlName(statement), parameter);
	}

	@Override
	public Map<String, Object> getOneMap(String mapper, Object para) {
		return this.getSqlSession().selectOne(mapper, para);
	}
	@Override
	public Long getLong(String statement, Object parameter) {
		return (Long) this.getSqlSession().selectOne(getSqlName(statement), parameter);
	}

	/**
	 * ************************************
	 * 统计数量
	 * @param 
	 * ************************************
	 */
	public Integer getCount(String statement){
		return (Integer) this.getSqlSession().selectOne(getSqlName(statement));
	}
	public Integer getCount(String statement,Object parameter){
		return (Integer) this.getSqlSession().selectOne(getSqlName(statement), parameter);
	}


	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	protected <S> S getMapper(Class<S> clazz) {
		return getSqlSession().getMapper(clazz);
	}
	
}
