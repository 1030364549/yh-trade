package com.yh.service.impl;

import com.yh.dao.AgentInterfaceDao;
import com.yh.entity.AgentInterface;
import com.yh.service.AgentInterfaceService;
import com.yh.util.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 〈成本管理ServiceImpl〉
 *
 * @author puyiliang
 * @date 2019/7/8  19:17
 * @since 1.0.0
 */
@Service
public class AgentInterfaceServiceImpl extends BaseServiceImpl<AgentInterface, String> implements AgentInterfaceService {

    @Resource
    private AgentInterfaceDao agentInterfaceDao;


    /**
     * 根据机构编号查询机构接口配置信息
     * @param params Map
     * @return Map
     * @throws Exception e
     */
    @Override
    public Map edit(Map params) throws Exception {
        Map<String, Object> agentInterfaceMap = agentInterfaceDao.getOneMap("getAgentInterfaceByAgentNum", params);
        return agentInterfaceMap;
    }

    /**
     * 配置机构进件接口配置
     * @param params Map
     * @throws Exception e
     */
    @Override
    public void saveAgentInterface(Map params, String[] allow_interface) throws Exception {
        StringBuffer sb = new StringBuffer();
        if (allow_interface.length != 0){
            for (String s : allow_interface) {
                sb.append("#").append(s).append("#").append(",");
            }
            sb.substring(0, sb.length() - 1);
        }
        params.put("allow_interface", sb.toString());
        //获取机构秘钥
        String agentKey = String.valueOf(params.get("agent_key"));
        if (!Utils.IsNull(agentKey)){
            //MD5加密
            String md5 = Utils.MD5(agentKey);
            params.put("agent_key",md5);
        }
        agentInterfaceDao.update("saveAgentInterface",params);
    }

    /**
     * 查询配置接口
     * @throws Exception e
     */
    @Override
    public List selectInterfaceInfo() throws Exception {
        return (List)agentInterfaceDao.getObjectList("selectInterfaceInfo");
    }

    /**
     * 查询配置接口
     * @throws Exception e
     */
    @Override
    public Map getInterface(Map params) throws Exception {
        if (Utils.IsNull(String.valueOf(params.get("id")))){
            //不存在ID 返回空
            return null;
        }
        return agentInterfaceDao.getOneMap("getInterface", params);
    }

    /**
     * 配置机构进件接口配置
     * @param params Map
     * @throws Exception e
     */
    @Override
    public void saveInterface(Map params) throws Exception {
        String id = String.valueOf(params.get("id"));
        if (Utils.IsNull(id)){
            params.put("addtime",Utils.formateDate(2));
            //新增
            agentInterfaceDao.insert("insertInterface",params);
        }else{
            //修改
            agentInterfaceDao.insert("updateInterface",params);
        }
    }
}
