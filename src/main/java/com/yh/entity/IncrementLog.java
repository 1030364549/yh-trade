package com.yh.entity;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Author: Liyh
 * @Date: 2019/10/14 13:46
 * @Description:   增值服务记录表
 * @Version: 1.0
 */
@SuppressWarnings("all")
public class IncrementLog implements Serializable{

    private int increment_log_id;
    private BigDecimal amount;
    private int type;
    private String  note;
    private int insurance_type;
    private String  create_date;
    private String  create_time;
    private String  create_name;
    private int agent_num;
    private String agent_name;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAgent_num() {
        return agent_num;
    }

    public void setAgent_num(int agent_num) {
        this.agent_num = agent_num;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public int getIncrement_log_id() {
        return increment_log_id;
    }

    public void setIncrement_log_id(int increment_log_id) {
        this.increment_log_id = increment_log_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getInsurance_type() {
        return insurance_type;
    }

    public void setInsurance_type(int insurance_type) {
        this.insurance_type = insurance_type;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
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
