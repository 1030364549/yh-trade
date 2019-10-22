package com.yh.entity;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * ************************************
 * XXX表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class Area implements Serializable {
	

	public String toString(){
		StringBuffer str=new StringBuffer();
		try {
			Method[] filed=this.getClass().getMethods();
			for (int i = 0; i < filed.length; i++) {
				String method_name=filed[i].getName();
				String three_name=method_name.substring(0, 3);
				if ("get".equals(three_name) && !"getClass".equals(method_name)) {
					str.append(method_name.substring(3).toLowerCase()).append("：").append(filed[i].invoke(this)).append("\r\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}
}
