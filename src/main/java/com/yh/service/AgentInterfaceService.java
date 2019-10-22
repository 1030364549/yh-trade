package com.yh.service;

import com.yh.entity.AgentInterface;

import java.util.List;
import java.util.Map;

/**
 * 〈成本管理Service〉
 *
 * @author puyiliang
 * @date 2019/7/8  19:10
 * @since 1.0.0
 */
public interface AgentInterfaceService extends BaseService<AgentInterface, String> {

    Map edit(Map params) throws Exception;

    void saveAgentInterface(Map params, String[] allow_interface) throws Exception;

    List selectInterfaceInfo() throws Exception;

    Map getInterface(Map params) throws Exception;

    void saveInterface(Map params) throws Exception;
}
