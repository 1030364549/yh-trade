package com.yh.entity;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ************************************
 * 菜单表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class BackCarte implements Serializable {
	
	private Integer id;//菜单ID
	private String carte_name;//菜单名称
	private String href;//菜单地址
	private String target;//打开方式：navTab、dialog
	private String carte_intro;//菜单介绍
	private String parent_id;//菜单标识
	private Integer levels;//级别
	private String parents;//父类菜单标识
	private Integer belong_terrace;//平台标识：0-后台
	private String rel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarte_name() {
		return carte_name;
	}
	public void setCarte_name(String carteName) {
		carte_name = carteName;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getCarte_intro() {
		return carte_intro;
	}
	public void setCarte_intro(String carteIntro) {
		carte_intro = carteIntro;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	public Integer getLevels() {
		return levels;
	}
	public void setLevels(Integer levels) {
		this.levels = levels;
	}
	public String getParents() {
		return parents;
	}
	public void setParents(String parents) {
		this.parents = parents;
	}
	public Integer getBelong_terrace() {
		return belong_terrace;
	}
	public void setBelong_terrace(Integer belongTerrace) {
		belong_terrace = belongTerrace;
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
