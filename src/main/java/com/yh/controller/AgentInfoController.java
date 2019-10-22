package com.yh.controller;

import com.yh.entity.AdminInfo;
import com.yh.entity.PropertiesData;
import com.yh.service.AccountsService;
import com.yh.service.AgentInfoService;
import com.yh.service.AgentRelatedService;
import com.yh.service.AreaService;
import com.yh.util.Utils;
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
 * ************************************
 * 代理商控制层
 *
 * @param <T>
 * @param <PK> ************************************
 */
@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("AgentInfo")
public class AgentInfoController extends BaseController {
    @Resource
    private AgentInfoService agentinfoservice;
    @Resource
    private AreaService areaService;
    @Resource
    private AccountsService accountsService;
    @Resource
    private AgentRelatedService agentRelatedService;

    /**
     * 机构列表查询
     * @author puyiliang
     * @return String
     */
    @RequiresPermissions("AgentInfo_list")
    @RequestMapping("list")
    public String list() {
        try {
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                this.getPage().getParams().put("obj_no",obj_no);
            }
            agentinfoservice.getPageList(this.getPage());
        } catch (Exception e) {
            printErrorMessage("查看机构列表:", e, this.request);
        }
        return this.display();
    }

    /**
     * 跳转 添加/修改机构 页面
     * @author puyiliang
     * @param agent_num String
     * @return String
     */
    @RequestMapping("agentInfoAdd")
    public String agentInfoAdd(@RequestParam Map agentInfo){
        try {
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                agentInfo.put("obj_no",obj_no);
            }
            //查询省份列表
            List<Map<String, Object>> areaMap = areaService.findArea("0");
            this.setAttribute("areaMap", areaMap);
            //如果主键存在， 表示为修改
            if (!Utils.IsNull((String)agentInfo.get("agent_num"))) {
                //查询机构和账户信息
                Map agentinfo = agentinfoservice.getAgentOne(agentInfo);
                this.setAttribute("agentinfo", agentinfo);
                //查询省所属市区地址表信息
                List<Map<String, Object>> shiMap = areaService.findArea(agentinfo.get("PROVINCE").toString());
                this.setAttribute("shiMap", shiMap);
                //根据账户开户地 省查市列表
                if(!Utils.IsNull(agentinfo.get("ACCOUNTS_PROVINCE"))){
                    List<Map<String, Object>> shiMap1 = areaService.findArea(agentinfo.get("ACCOUNTS_PROVINCE").toString());
                    this.setAttribute("shiMap1", shiMap1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("添加/更新机构跳转:",e,this.request);
        }
        return this.display();
    }

    /**
     * 添加/修改机构
     * @author puyiliang
     * @param agentinfo Map
     * @return Map
     */
    @RequestMapping("saveAgentInfo")
    @ResponseBody
    public Map saveAgentInfo(@RequestParam Map agentinfo){
        try {
            //非顶级机构限制
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                agentinfo.put("obj_no",obj_no);
            }
            //主键不存在 新增机构信息
            if (Utils.IsNull(agentinfo.get("agent_num"))){
                //获取登陆人信息
                AdminInfo adminInfo = this.getAdminInfo();
                //添加人
                agentinfo.put("add_name",adminInfo.getAdmin_name());
                agentinfo.put("createman",adminInfo.getAdmin_name());
                agentinfoservice.saveAgentInfo(agentinfo);
                return message("添加成功！", 0);
            }else{
                agentinfoservice.updateAgentInfo(agentinfo);
                return message("修改成功！", 0);
            }
        } catch (Exception e) {
            printErrorMessage("添加/修改机构信息:", e, this.request);
            return message("添加/修改机构信息", false);
        }
    }

    /**
     * 开启/关闭机构状态
     * @author puyiliang
     * @param agentinfo Map
     * @return Map
     */
    @RequestMapping("updateAgentType")
    @ResponseBody
    public Map updateAgentType(@RequestParam Map agentInfo){
        try {
            //非顶级机构限制
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                agentInfo.put("obj_no",obj_no);
            }
            agentinfoservice.updateAgentType(agentInfo);
            return message("开启/关闭机构成功！", true);
        } catch (Exception e) {
            printErrorMessage("开启/关闭机构状态:", e, this.request);
            return message("开启/关闭机构异常！", false);
        }
    }
}

