
package com.yh.entity;

import java.io.Serializable;
import java.lang.reflect.Method;

/*
 *********************************************************.<br>
 * [类名] Buttons <br>
 * [描述] 按钮表 <br>
 * [作者] BLUE <br>
 * [时间] 2018年10月26日 下午5:07:37 <br>
 *********************************************************.<br>
 */
@SuppressWarnings("all")
public class Buttons implements Serializable{
	
	private Integer bt_id;//按钮ID
	private String bt_name;//按钮名称
	private String bt_href;//本级地址
	private String bt_sc_href;//子级地址
	private Integer carte_id;//菜单ID
	private String addtime;//添加时间
	private String remark;//备注
	private String rel;//按钮REL(用于按钮权限)
	
	
	public Integer getBt_id() {
	
		return bt_id;
	}
	public void setBt_id(Integer bt_id) {
	
		this.bt_id = bt_id;
	}
	public String getBt_name() {
	
		return bt_name;
	}
	public void setBt_name(String bt_name) {
	
		this.bt_name = bt_name;
	}
	public String getBt_href() {
	
		return bt_href;
	}
	public void setBt_href(String bt_href) {
	
		this.bt_href = bt_href;
	}
	public String getBt_sc_href() {
	
		return bt_sc_href;
	}
	public void setBt_sc_href(String bt_sc_href) {
	
		this.bt_sc_href = bt_sc_href;
	}
	public Integer getCarte_id() {
	
		return carte_id;
	}
	public void setCarte_id(Integer carte_id) {
	
		this.carte_id = carte_id;
	}
	public String getAddtime() {
	
		return addtime;
	}
	public void setAddtime(String addtime) {
	
		this.addtime = addtime;
	}
	public String getRemark() {
	
		return remark;
	}
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	public String getRel() {
		
		return rel;
	}
	public void setRel(String rel) {
	
		this.rel = rel;
	}
	
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

	