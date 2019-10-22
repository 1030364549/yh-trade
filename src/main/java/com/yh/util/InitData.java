package com.yh.util;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.yh.entity.PropertiesData;
/**
 * ************************************
 * 数据初始化:资源文件参数皆在此初始化<监听器>
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class InitData implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		Properties p=new Properties();
		InputStream inputStream=null;
		try {
			//加载配置文件
			inputStream=this.getClass().getResourceAsStream("/message.properties");
			p.load(inputStream);
			//解析配置文件
			PropertiesData.adminPass=p.get("adminPass").toString();
			
			//解析结束
			inputStream.close();
			p.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
