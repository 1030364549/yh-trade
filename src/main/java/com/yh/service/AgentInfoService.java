package com.yh.service;

import com.yh.entity.AdminInfo;
import com.yh.entity.AgentInfo;
import com.yh.entity.Page;

import java.util.Map;

/**
 * ************************************
 * 代理商Service
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public interface AgentInfoService extends BaseService<AgentInfo, Integer> {


    void getPageList(Page page) throws Exception;

    void saveAgentInfo(Map agentinfo) throws Exception;

    Map getAgentOne(Map agentInfo) throws Exception;

    void updateAgentInfo(Map agentinfo) throws Exception;

    void updateAgentType(Map agentInfo) throws Exception;

    Map agentRelatedAdd(Map agentRelated) throws Exception;

}
