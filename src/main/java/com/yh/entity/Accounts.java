package com.yh.entity;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
/**
 * ************************************
 * XXX表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@Data
public class Accounts implements Serializable {

	private String id;
	private String bank_name;
	private String screen_name;
	private String screen_num;
	private String bank_num;
	private String seq_id;
	private String da_marker;
	private Integer nature;
	private String createman;
	private Date createdate;
	private String status;
	private String isallaudit;
	private String note;
	private String acmoney;
	private String resphone;
	
}
