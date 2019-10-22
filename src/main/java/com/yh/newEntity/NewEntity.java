package com.yh.newEntity;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * ************************************
 * 表名生成类
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class NewEntity {
	static String table_name="PROFIT_SETT_BILL";//表名

	public static void main(String[] args) {
		try {
			new NewEntity().write(table_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ok");
	}
	
	
	
	
	/****************************实现******************************/
	private String pack="com.yh";//包路径
	private String locahost = "";//java代码生成路径
	
	public void write(String table) throws Exception{
		locahost = System.getProperty("user.dir")+"\\src\\main\\java\\";
		
		try {
			String entity_name=getEntityName(table);
			
			add_sql(entity_name);
			add_entity(entity_name);
			add_dao(entity_name);
			add_daoImpl(entity_name);
			add_service(entity_name);
			add_serviceImpl(entity_name);
			add_controller(entity_name);
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getEntityName <br>
	 * [描述] 生成对象名<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public String getEntityName(String table){
		String en_name="";
		String[] t_name=table.toLowerCase().split("_");
		for (int i = 0; i < t_name.length; i++) {
			en_name+=t_name[i].substring(0,1).toUpperCase()+t_name[i].substring(1);
		}
		return en_name;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] writeEntity <br>
	 * [描述] 写入实体类<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void writeEntity(String file,String conTxt){
		try {
			FileOutputStream outs = new FileOutputStream(file, false);
			PrintStream p = new PrintStream(outs);
			p.println(conTxt);
			p.flush();
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] add_sql <br>
	 * [描述] 添加该类的XML文件<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void add_sql(String entity_name){
		String newFile=System.getProperty("user.dir")+"\\src\\main\\resources\\mybatise\\mapper\\"+entity_name+".xml";
		StringBuffer entity_txt=new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\r\n");
		entity_txt.append(
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append("\r\n");
		entity_txt.append("<mapper namespace=\"").append(entity_name).append("\">").append("\r\n");
		entity_txt.append("	").append("\r\n").append("	").append("\r\n");
		entity_txt.append("</mapper>");
		
		writeEntity(newFile,entity_txt.toString());
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] add_entity <br>
	 * [描述] 添加该类的实体类<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void add_entity(String entity_name){
		String packs=pack+".entity";
		String newFile=locahost+packs.replace(".", "\\")+"\\"+entity_name+".java";
		StringBuffer entity_txt=new StringBuffer("package com.yh.entity;").append("\r\n\r\n");
		entity_txt.append("import java.io.Serializable;").append("\r\n");
		entity_txt.append("import java.lang.reflect.Method;").append("\r\n");
		entity_txt.append("/**").append("\r\n").append(" * ************************************").append("\r\n");
		entity_txt.append(" * XXX表").append("\r\n").append(" * @param <T>").append("\r\n");
		entity_txt.append(" * @param <PK>").append("\r\n").append(" * ************************************").append("\r\n");
		entity_txt.append(" */").append("\r\n").append("@SuppressWarnings(\"all\")").append("\r\n");
		entity_txt.append("public class ").append(entity_name).append(" implements Serializable {").append("\r\n");
		entity_txt.append("	").append("\r\n\r\n");
		entity_txt.append("	public String toString(){").append("\r\n");
		entity_txt.append("		StringBuffer str=new StringBuffer();").append("\r\n");
		entity_txt.append("		try {").append("\r\n");
		entity_txt.append("			Method[] filed=this.getClass().getMethods();").append("\r\n");
		entity_txt.append("			for (int i = 0; i < filed.length; i++) {").append("\r\n");
		entity_txt.append("				String method_name=filed[i].getName();").append("\r\n");
		entity_txt.append("				String three_name=method_name.substring(0, 3);").append("\r\n");
		entity_txt.append("				if (\"get\".equals(three_name) && !\"getClass\".equals(method_name)) {").append("\r\n");
		entity_txt.append("					str.append(method_name.substring(3).toLowerCase()).append(\"：\").append(filed[i].invoke(this)).append(\"\\r\\n\");").append("\r\n");
		entity_txt.append("				}").append("\r\n");
		entity_txt.append("			}").append("\r\n");
		entity_txt.append("		} catch (Exception e) {").append("\r\n");
		entity_txt.append("			e.printStackTrace();").append("\r\n");
		entity_txt.append("		}").append("\r\n");
		entity_txt.append("		return str.toString();").append("\r\n");
		entity_txt.append("	}").append("\r\n");
		entity_txt.append("}");
		
		writeEntity(newFile,entity_txt.toString());
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] add_dao <br>
	 * [描述] 添加该类的dao层<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void add_dao(String entity_name){
		String packs=pack+".dao";
		String newFile=locahost+packs.replace(".", "\\")+"\\"+entity_name+"Dao.java";
		StringBuffer entity_txt=new StringBuffer("package com.yh.dao;").append("\r\n\r\n");
		entity_txt.append("import com.yh.entity.").append(entity_name).append(";\r\n");
		entity_txt.append("/**").append("\r\n");
		entity_txt.append(" * ************************************").append("\r\n");
		entity_txt.append(" * dao层：XXX表").append("\r\n");
		entity_txt.append(" * @param <T>").append("\r\n");
		entity_txt.append(" * @param <PK>").append("\r\n");
		entity_txt.append(" * ************************************").append("\r\n");
		entity_txt.append(" */").append("\r\n");
		entity_txt.append("public interface ").append(entity_name).append("Dao extends BaseDao<").append(entity_name).append(", Integer> {").append("\r\n");
		entity_txt.append("").append("\r\n");
		entity_txt.append("}");
		
		writeEntity(newFile,entity_txt.toString());
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] add_daoImpl <br>
	 * [描述] 添加该类的dao实现层<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void add_daoImpl(String entity_name){
		String packs=pack+".dao.impl";
		String newFile=locahost+packs.replace(".", "\\")+"\\"+entity_name+"DaoImpl.java";
		StringBuffer entity_txt=new StringBuffer("package com.yh.dao.impl;").append("\r\n\r\n");
		entity_txt.append("import org.springframework.stereotype.Repository;").append("\r\n");
		entity_txt.append("import com.yh.dao.").append(entity_name).append("Dao;\r\n");
		entity_txt.append("import com.yh.entity.").append(entity_name).append(";\r\n");
		entity_txt.append("/**").append("\r\n");
		entity_txt.append(" * ************************************").append("\r\n");
		entity_txt.append(" * dao实现层：XXX表").append("\r\n");
		entity_txt.append(" * @param <T>").append("\r\n");
		entity_txt.append(" * @param <PK>").append("\r\n");
		entity_txt.append(" * ************************************").append("\r\n");
		entity_txt.append(" */").append("\r\n");
		entity_txt.append("@Repository").append("\r\n");
		entity_txt.append("public class ").append(entity_name).append("DaoImpl extends BaseDaoImpl<").append(entity_name).append(", Integer> ");
		entity_txt.append("implements").append("\r\n").append("		").append(entity_name).append("Dao {").append("\r\n");
		entity_txt.append("").append("\r\n");
		entity_txt.append("}");
		
		writeEntity(newFile,entity_txt.toString());
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] add_service <br>
	 * [描述] 添加该类的service层<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void add_service(String entity_name){
		String packs=pack+".service";
		String newFile=locahost+packs.replace(".", "\\")+"\\"+entity_name+"Service.java";
		StringBuffer entity_txt=new StringBuffer("package com.yh.service;").append("\r\n\r\n");
		entity_txt.append("import com.yh.entity.").append(entity_name).append(";\r\n");
		entity_txt.append("/**").append("\r\n");
		entity_txt.append(" * ************************************").append("\r\n");
		entity_txt.append(" * XXXService").append("\r\n");
		entity_txt.append(" * @param <T>").append("\r\n");
		entity_txt.append(" * @param <PK>").append("\r\n");
		entity_txt.append(" * ************************************").append("\r\n");
		entity_txt.append(" */").append("\r\n");
		entity_txt.append("@SuppressWarnings(\"all\")").append("\r\n");
		entity_txt.append("public interface ").append(entity_name).append("Service extends BaseService<").append(entity_name).append(", Integer> {").append("\r\n");
		entity_txt.append("").append("\r\n");
		entity_txt.append("}");
		
		writeEntity(newFile,entity_txt.toString());
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] add_serviceImpl <br>
	 * [描述] 添加该类的service实现层<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void add_serviceImpl(String entity_name){
		String first=entity_name.substring(0,1).toLowerCase();
		String end=entity_name.substring(1);
		String packs=pack+".service.impl";
		String newFile=locahost+packs.replace(".", "\\")+"\\"+entity_name+"ServiceImpl.java";
		StringBuffer entity_txt=new StringBuffer("package com.yh.service.impl;").append("\r\n\r\n");
		entity_txt.append("import javax.annotation.Resource;").append("\r\n\r\n");
		entity_txt.append("import org.springframework.stereotype.Service;").append("\r\n\r\n");
		entity_txt.append("import com.yh.dao.").append(entity_name).append("Dao;").append("\r\n");
		entity_txt.append("import com.yh.entity.").append(entity_name).append(";\r\n");
		entity_txt.append("import com.yh.service.").append(entity_name).append("Service;").append("\r\n\r\n");
		entity_txt.append("@SuppressWarnings(\"all\")").append("\r\n");
		entity_txt.append("@Service").append("\r\n");
		entity_txt.append("public class ").append(entity_name).append("ServiceImpl extends BaseServiceImpl<").append(entity_name).append(", Integer> ").append("\r\n");
		entity_txt.append("		implements ").append(entity_name).append("Service {").append("\r\n");
		entity_txt.append("	@Resource").append("\r\n");
		entity_txt.append("	private ").append(entity_name).append("Dao ").append(first).append(end).append("Dao;").append("\r\n");
		entity_txt.append("").append("\r\n\r\n\r\n\r\n");
		entity_txt.append("}");
		
		writeEntity(newFile,entity_txt.toString());
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] add_controller <br>
	 * [描述] 添加该类的控制层<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public void add_controller(String entity_name){
		String first=entity_name.substring(0,1).toLowerCase();
		String end=entity_name.substring(1);
		String packs=pack+".controller";
		String newFile=locahost+packs.replace(".", "\\")+"\\"+entity_name+"Controller.java";
		StringBuffer entity_txt=new StringBuffer("package com.yh.controller;").append("\r\n\r\n");
		entity_txt.append("import javax.annotation.Resource;").append("\r\n\r\n");
		entity_txt.append("import org.springframework.context.annotation.Scope;").append("\r\n");
		entity_txt.append("import org.springframework.stereotype.Controller;").append("\r\n");
		entity_txt.append("import org.springframework.web.bind.annotation.RequestMapping;").append("\r\n\r\n");
		
		entity_txt.append("import com.yh.service.").append(entity_name).append("Service;").append("\r\n\r\n");
		
		
		entity_txt.append("@SuppressWarnings(\"all\")").append("\r\n");
		entity_txt.append("@Scope(\"prototype\")").append("\r\n");
		entity_txt.append("@Controller").append("\r\n");
		entity_txt.append("@RequestMapping(\"").append(entity_name).append("\")").append("\r\n");
		entity_txt.append("public class ").append(entity_name).append("Controller extends BaseController{").append("\r\n");
		entity_txt.append("	@Resource").append("\r\n");
		entity_txt.append("	private ").append(entity_name).append("Service ").append(first).append(end).append("Service;").append("\r\n\r\n");
		
		entity_txt.append("}");
		
		writeEntity(newFile,entity_txt.toString());
	}
	
}
