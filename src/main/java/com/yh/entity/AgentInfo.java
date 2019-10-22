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
public class AgentInfo implements Serializable{
	
	private String agent_num;//代理商编号，主键ID
	private String agent_name;//代理商名称
	private String agent_area;//代理商地址
	private String commissary;//代理商姓名
	private String identity_num;//身份证
	private String cor_phone;//手机号
	private Integer agent_status;//状态 0正常代理 1申请代理 2代理审核中 3申请打回 4代理注销
	private Integer agent_level;//代理等级-1顶级服务商 0服务商 1一级代理商
	private String belong_agent;//所属代理编号
	private String add_name;//添加人
	private String localdate;//添加日期
	private String localtime;//添加时间
	private String note;//备注
	private String area_code;//区域码
	private String sm_num;//业务员编号
	
	private String admin_id;
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
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
	public String getAtt() {
		return att;
	}
	public void setAtt(String att) {
		this.att = att;
	}
	public String getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRole_num() {
		return role_num;
	}
	public void setRole_num(String role_num) {
		this.role_num = role_num;
	}

	private String admin_name;
	private String admin_realname;
	private String admin_pass;
	private String admin_intro;
	private String att;
	private String is_admin;
	private String type;
	private String role_num;
	
	public String getSm_num() {
		return sm_num;
	}
	public void setSm_num(String sm_num) {
		this.sm_num = sm_num;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	private String sheng;
	private String shi;
	private String qu;
	
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getQu() {
		return qu;
	}
	public void setQu(String qu) {
		this.qu = qu;
	}
	public String getAgent_num() {
		return agent_num;
	}
	public void setAgent_num(String agent_num) {
		this.agent_num = agent_num;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getAgent_area() {
		return agent_area;
	}
	public void setAgent_area(String agent_area) {
		this.agent_area = agent_area;
	}
	public String getCommissary() {
		return commissary;
	}
	public void setCommissary(String commissary) {
		this.commissary = commissary;
	}
	public String getIdentity_num() {
		return identity_num;
	}
	public void setIdentity_num(String identity_num) {
	
		this.identity_num = identity_num;
	}
	public String getCor_phone() {
		return cor_phone;
	}
	public void setCor_phone(String cor_phone) {
		this.cor_phone = cor_phone;
	}
	public Integer getAgent_status() {
		return agent_status;
	}
	public void setAgent_status(Integer agent_status) {
		this.agent_status = agent_status;
	}
	public Integer getAgent_level() {
		return agent_level;
	}
	public void setAgent_level(Integer agent_level) {
		this.agent_level = agent_level;
	}
	public String getBelong_agent() {
		return belong_agent;
	}
	public void setBelong_agent(String belong_agent) {
		this.belong_agent = belong_agent;
	}
	public String getAdd_name() {
		return add_name;
	}
	public void setAdd_name(String add_name) {
		this.add_name = add_name;
	}
	public String getLocaldate() {
		return localdate;
	}
	public void setLocaldate(String localdate) {
		this.localdate = localdate;
	}
	public String getLocaltime() {
		return localtime;
	}
	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
