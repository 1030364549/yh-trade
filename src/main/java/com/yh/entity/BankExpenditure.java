
package com.yh.entity;

import java.io.Serializable;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public class BankExpenditure implements Serializable{
	
	private Integer serial;//交易流水号，主键ID
	private String msgtype;//交易类型
	private String posno;//终端号
	private String localdate;//交易日期
	private String localtime;//交易时间
	private Integer bid;//通道编号
	private String pan;// 交易卡号
	private String amount;//交易金额
	private String rc;//交易结果
	private Integer status;//交易状态
	private String stan;//渠道终端号
	private String rrno;//渠道参考号
	private String air;//授权码
	private String dd;//添加日期
	private String cati;//渠道终端号
	private String merno;//渠道商户号
	private String inputtype;//磁条 芯片
	private Integer cardtype;//卡类型
	private String clrgdt;//渠道清算日期
	private Integer sett_type;//结算类型
	private Integer sett_status;//清算类型
	private Integer check_status;//对账状态
	
	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getPosno() {
		return posno;
	}

	public void setPosno(String posno) {
		this.posno = posno;
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

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStan() {
		return stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getRrno() {
		return rrno;
	}

	public void setRrno(String rrno) {
		this.rrno = rrno;
	}

	public String getAir() {
		return air;
	}

	public void setAir(String air) {
		this.air = air;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getCati() {
		return cati;
	}

	public void setCati(String cati) {
		this.cati = cati;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public String getInputtype() {
		return inputtype;
	}

	public void setInputtype(String inputtype) {
		this.inputtype = inputtype;
	}

	public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	public String getClrgdt() {
		return clrgdt;
	}

	public void setClrgdt(String clrgdt) {
		this.clrgdt = clrgdt;
	}

	public Integer getSett_type() {
		return sett_type;
	}

	public void setSett_type(Integer sett_type) {
		this.sett_type = sett_type;
	}

	public Integer getSett_status() {
		return sett_status;
	}

	public void setSett_status(Integer sett_status) {
		this.sett_status = sett_status;
	}

	public Integer getCheck_status() {
		return check_status;
	}

	public void setCheck_status(Integer check_status) {
		this.check_status = check_status;
	}
	
	@Override
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

	