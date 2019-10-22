package com.yh.entity;

import java.lang.reflect.Method;
@SuppressWarnings("all")
public class InsuranceChannel {

    private int insurance_channel_id;
    private String channel_no;
    private String channel_name;
    private int channel_type;
    private int channel_status;
    private String channel_cost;

    public int getInsurance_channel_id() {
        return insurance_channel_id;
    }

    public void setInsurance_channel_id(int insurance_channel_id) {
        this.insurance_channel_id = insurance_channel_id;
    }

    public String getChannel_no() {
        return channel_no;
    }

    public void setChannel_no(String channel_no) {
        this.channel_no = channel_no;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public int getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(int channel_type) {
        this.channel_type = channel_type;
    }

    public int getChannel_status() {
        return channel_status;
    }

    public void setChannel_status(int channel_status) {
        this.channel_status = channel_status;
    }

    public String getChannel_cost() {
        return channel_cost;
    }

    public void setChannel_cost(String channel_cost) {
        this.channel_cost = channel_cost;
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
