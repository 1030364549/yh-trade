package com.yh.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 〈成本管理〉
 *
 * @author puyiliang
 * @date 2019/7/8  19:12
 * @since 1.0.0
 */
public class AgentRelated implements Serializable {
    private Integer id;
    private String agent_num;
    private String rated;
    private String rates;
    private String ratetype;
    private String agent_name;
    private String add_date;
    private String add_time;
    private String add_man;
    private String modifynote;
    private String credit_type;

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getCredit_type() {
        return credit_type;
    }

    public void setCredit_type(String credit_type) {
        this.credit_type = credit_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgent_num() {
        return agent_num;
    }

    public void setAgent_num(String agent_num) {
        this.agent_num = agent_num;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getRates() {
        return rates;
    }

    public void setRetes(String retes) {
        this.rates = retes;
    }

    public String getRatetype() {
        return ratetype;
    }

    public void setRatetype(String ratetype) {
        this.ratetype = ratetype;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAdd_man() {
        return add_man;
    }

    public void setAdd_man(String add_man) {
        this.add_man = add_man;
    }

    public String getModifynote() {
        return modifynote;
    }

    public void setModifynote(String modifynote) {
        this.modifynote = modifynote;
    }

    public AgentRelated() {
    }

    public AgentRelated(Map agentInfo) {
        this.add_man = (String) agentInfo.get("add_man");
        this.add_date = (String) agentInfo.get("add_date");
        this.add_time = (String) agentInfo.get("add_time");
        this.agent_num = (String) agentInfo.get("agent_num");
        this.agent_name = (String) agentInfo.get("agent_name");
    }
}
