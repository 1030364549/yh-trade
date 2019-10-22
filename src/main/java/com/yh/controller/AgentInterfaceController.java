package com.yh.controller;

import com.yh.aopshiro.BlueLog;
import com.yh.entity.PropertiesData;
import com.yh.service.AgentInterfaceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
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
@RequestMapping("AgentInterface")
public class AgentInterfaceController extends BaseController {

    @Resource
    private AgentInterfaceService agentInterfaceService;


    /**
     * 跳转机构进件接口配置页面
     * @param params Map
     * @return String
     */
    @RequiresPermissions("AgentInterface_edit")
    @RequestMapping("edit")
    public String edit(@RequestParam Map params){
        try {
            //非顶级机构限制
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                params.put("obj_no",obj_no);
            }
            Map result = agentInterfaceService.edit(params);
            List allowInterface = agentInterfaceService.selectInterfaceInfo();
            this.setAttribute("result", result);
            this.setAttribute("allowInterface", allowInterface);
        } catch (Exception e) {
            printErrorMessage("用户列表:",e,this.request);
        }
        return this.display();
    }

    /**
     * 机构进件接口配置
     * @param params Map
     * @return Map
     */
    @RequestMapping("saveAgentInterface")
    @ResponseBody
    @BlueLog("机构管理,机构管理,配置进件接口")//模块名称,菜单名称,功能名称
    public Map saveAgentInterface(@RequestParam(value = "allow_interface[]", required = false) String[] allow_interface, @RequestParam Map params){
        try {
            //获取登陆人信息
            params.put("adminName", this.getAdminInfo().getAdmin_realname());
            agentInterfaceService.saveAgentInterface(params, allow_interface);
            return message("机构进件接口配置！", 0);
        } catch (Exception e) {
            printErrorMessage("机构进件接口配置:", e, this.request);
            return message("机构进件接口配置", 1);
        }
    }

    /**
     * 跳转新增通行接口页面
     * @param params Map
     * @return String
     */
    @RequestMapping("addInterface")
    public String addInterface(@RequestParam Map params){
        try {
            //非顶级机构限制
            Map result = agentInterfaceService.getInterface(params);
            this.setAttribute("result", result);
        } catch (Exception e) {
            printErrorMessage("跳转新增通行接口页面:",e,this.request);
        }
        return this.display();
    }

    /**
     * 新增或修改通行接口
     * @param params Map
     * @return String
     */
    @RequestMapping("saveInterface")
    @ResponseBody
    public Map saveInterface(@RequestParam Map params){
        try {
            params.put("addname",this.getAdminInfo().getAdmin_realname());
            agentInterfaceService.saveInterface(params);
            return message("机构进件接口配置成功！", 0);
        } catch (Exception e) {
            printErrorMessage("机构进件接口配置异常:",e,this.request);
            return message("机构进件接口配置失败！", false);
        }
    }
}
