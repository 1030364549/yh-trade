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
public class AgentInterface implements Serializable {

    private String agent_num;
    private String agent_name;
    private String key;
    private String allow_interface;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAllow_interface() {
        return allow_interface;
    }

    public void setAllow_interface(String allow_interface) {
        this.allow_interface = allow_interface;
    }
}
