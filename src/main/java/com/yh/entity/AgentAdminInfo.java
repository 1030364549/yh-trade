package com.yh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ************************************
 * 用户表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class AgentAdminInfo implements Serializable{

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_realname() {
		return admin_realname;
	}

	public void setAdmin_realname(String admin_realname) {
		this.admin_realname = admin_realname;
	}

	public String getAdmin_pass() {
		return admin_pass;
	}

	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}

	public String getAdmin_intro() {
		return admin_intro;
	}

	public void setAdmin_intro(String admin_intro) {
		this.admin_intro = admin_intro;
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

	public void setIs_admin(Integer is_admin) {
		this.is_admin = is_admin;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRole_num() {
		return role_num;
	}

	public void setRole_num(String role_num) {
		this.role_num = role_num;
	}

	private Integer admin_id;//用户ID
	private String admin_name;//用户名
	private String admin_realname;//真实姓名
	private String admin_pass;//密码
	private String admin_intro;//备注
	private Integer att;//用户状态 0-启用、1-禁用
	private Integer is_admin;//是否管理员 0-是、1-否
	private Integer type;//0代理 1 销售员
	private String role_num;
}
