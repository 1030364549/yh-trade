package com.yh.service;

import com.yh.entity.AdminInfo;
import com.yh.entity.AgentRelated;
import com.yh.entity.Page;

import java.util.Map;

/**
 * 〈成本管理Service〉
 *
 * @author puyiliang
 * @date 2019/7/8  19:10
 * @since 1.0.0
 */
public interface AgentRelatedService extends BaseService<AgentRelated, Integer> {
    void getPageList(Page page) throws Exception;

    Map getOne(String id) throws Exception;

    void updateAgentRelated(Map agentRetaled, AdminInfo adminInfo) throws Exception;

    void saveAgentRelated(Map agentinfo, AdminInfo adminInfo) throws Exception;
}
