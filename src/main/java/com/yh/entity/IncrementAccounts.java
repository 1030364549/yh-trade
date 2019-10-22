package com.yh.entity;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Author: Liyh
 * @Date: 2019/10/14 13:55
 * @Description:   增值服务账户表
 * @Version: 1.0
 */
@SuppressWarnings("all")
public class IncrementAccounts implements Serializable {

    private int agent_num;          //机构编号，主键ID
    private BigDecimal insurance_amount;   //保险账户剩余金额
    private BigDecimal  message_amount;     //短信账户剩余金额
    private BigDecimal  amount;             //账户剩余金额
    private int     is_currency;        //账户是否通用 1通用 2不通用
    private String  agent_name;         //机构名称

    public Integer getAgent_num() {
        return agent_num;
    }

    public void setAgent_num(Integer agent_num) {
        this.agent_num = agent_num;
    }

    public void setAgent_num(int agent_num) {
        this.agent_num = agent_num;
    }

    public BigDecimal getInsurance_amount() {
        return insurance_amount;
    }

    public void setInsurance_amount(BigDecimal insurance_amount) {
        this.insurance_amount = insurance_amount;
    }

    public BigDecimal getMessage_amount() {
        return message_amount;
    }

    public void setMessage_amount(BigDecimal message_amount) {
        this.message_amount = message_amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getIs_currency() {
        return is_currency;
    }

    public void setIs_currency(int is_currency) {
        this.is_currency = is_currency;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
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
