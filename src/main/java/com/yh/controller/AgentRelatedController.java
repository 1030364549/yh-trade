package com.yh.controller;

import com.yh.entity.AdminInfo;
import com.yh.entity.PropertiesData;
import com.yh.service.AgentInfoService;
import com.yh.service.AgentRelatedService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 〈成本管理Controller〉
 *
 * @author puyiliang
 * @date 2019/7/8  19:09
 * @since 1.0.0
 */
@Scope("prototype")
@Controller
@RequestMapping("AgentRelated")
public class AgentRelatedController extends BaseController {

    @Resource
    private AgentRelatedService agentRelatedService;

    @Resource
    private AgentInfoService agentInfoService;

    /**
     * 成本管理列表
     * @author puyiliang
     * @return String
     */
    @RequiresPermissions("AgentRelated_list")
    @RequestMapping("list")
    public String list(){
        try {
            //非顶级机构限制
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                this.getPage().getParams().put("obj_no",obj_no);
            }
            agentRelatedService.getPageList(this.getPage());
        } catch (Exception e) {
            printErrorMessage("用户列表:",e,this.request);
        }
        return this.display();
    }

    /**
     * 跳转机构成本添加
     * @author puyiliang
     * @param agentRelated Map
     * @return Map
     */
    @RequestMapping("agentRelatedAdd")
    public String agentRelatedAdd(@RequestParam Map agentRelated){
        try {
            //非顶级机构限制
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                agentRelated.put("obj_no",obj_no);
            }
            Map agentinfo = agentInfoService.agentRelatedAdd(agentRelated);
            this.setAttribute("agentinfo", agentinfo);
        } catch (Exception e) {
            printErrorMessage("跳转机构成本添加:", e, this.request);
        }
        return this.display();
    }
    /**
     * 成本修改跳转
     * @author puyiliang
     * @return String
     */
    @RequestMapping("agentRelatedUpd")
    public String agentRelatedUpd(@RequestParam Map agentRelated){
        try {
            Map agentinfo = agentInfoService.agentRelatedAdd(agentRelated);
            this.setAttribute("agentinfo", agentinfo);
        } catch (Exception e) {
            printErrorMessage("跳转机构成本修改:", e, this.request);
        }
        return this.display();
    }

    /**
     * 添加机构成本
     * @author puyiliang
     * @param agentinfo Map
     * @return Map
     */
    @RequestMapping("saveAgentRelated")
    @ResponseBody
    public Map saveAgentRelated(@RequestParam Map agentinfo){
        try {
            //获取登陆人信息
            AdminInfo adminInfo = this.getAdminInfo();
            agentRelatedService.saveAgentRelated(agentinfo, adminInfo);
            return message("添加机构成本成功！", 0);
        } catch (Exception e) {
            printErrorMessage("添加机构成本信息:", e, this.request);
            return message("添加机构成本异常", 1);
        }
    }

    /**
     * 成本修改
     * @author puyiliang
     * @return String
     */
    @RequestMapping("updateAgentRelated")
    @ResponseBody
    public Map updateAgentRelated(@RequestParam Map agentRelated){
        try {
            AdminInfo adminInfo = this.getAdminInfo();
            agentRelatedService.updateAgentRelated(agentRelated, adminInfo);
            return message("修改机构成本成功！", 0);
        } catch (Exception e) {
            printErrorMessage("修改机构成本信息:",e,this.request);
            return message("修改机构成本异常", 1);
        }
    }

}
