package com.yh.entity;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * ************************************
 * 用户表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class AdminInfo implements Serializable{
	
	private Integer admin_id;//用户ID
	private String admin_name;//用户名
	private String admin_realname;//真实姓名
	private String admin_pass;//密码
	private String admin_intro;//备注
	private Integer att;//用户状态 0-启用、1-禁用
	private Integer is_admin;//是否管理员 0-是、1-否
	private Integer belong;//平台标识0-总后台
	private Integer obj_no;//对象编号[代理商、业务员]
	private Integer sm_num;// 业务员编号
	private String mername;

	public Integer getObj_no() {
		return obj_no;
	}

	public void setObj_no(Integer obj_no) {
		this.obj_no = obj_no;
	}

	public String getMername() {
		return mername;
	}
	public void setMername(String mername) {
		this.mername = mername;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer adminId) {
		admin_id = adminId;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String adminName) {
		admin_name = adminName;
	}
	public String getAdmin_realname() {
		return admin_realname;
	}
	public void setAdmin_realname(String adminRealname) {
		admin_realname = adminRealname;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String adminPass) {
		admin_pass = adminPass;
	}
	public String getAdmin_intro() {
		return admin_intro;
	}
	public void setAdmin_intro(String adminIntro) {
		admin_intro = adminIntro;
	}
	public Integer getAtt() {
		return att;
	}
	public void setAtt(Integer att) {
		this.att = att;
	}
	public Integer getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(Integer isAdmin) {
		is_admin = isAdmin;
	}
	public Integer getBelong() {
		return belong;
	}
	public void setBelong(Integer belong) {
		this.belong = belong;
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
	public Integer getSm_num() {
	
		return sm_num;
	}
	public void setSm_num(Integer sm_num) {
	
		this.sm_num = sm_num;
	}
}
