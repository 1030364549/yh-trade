package com.yh.controller;

import com.yh.entity.AdminInfo;
import com.yh.entity.PropertiesData;
import com.yh.service.AgentInfoService;
import com.yh.service.IncrementAccountsService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Liyh
 * @Date: 2019/10/14 14:00
 * @Description:   增值服务
 * @Version: 1.0
 */
@Scope("prototype")
@Controller
@RequestMapping("IncrementAccounts")
public class IncrementAccountsController extends BaseController {

    @Resource
    private IncrementAccountsService incrementAccountsService;
    @Resource
    private AgentInfoService agentInfoService;
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 15:38
     * @Description: 机构增值服务列表查询
     * @Version: 1.0
     */
    @RequestMapping("list")
    public String list() {
        try {
            Integer obj_no = this.getAdminInfo().getObj_no();
            if (!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))) {
                this.getPage().getParams().put("obj_no", obj_no);
            }
            incrementAccountsService.getPageList(this.getPage());
        } catch (Exception e) {
            printErrorMessage("查看增值服务账户表:", e, this.request);
        }
        return this.display();
    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 17:44
     * @Description:    充值余额跳转
     * @Version: 1.0
     */
//    @RequiresPermissions("IncrementAccounts_addBalance")
    @RequestMapping("addBalance")
    public String addBalance(@RequestParam Map incrementAccounts){
        try {

            Map result = incrementAccountsService.getIncrementAccountsOne(incrementAccounts);
            this.setAttribute("incrementAccounts", result);
        } catch (Exception e) {
            printErrorMessage("充值余额跳转:", e, this.request);
        }
        return this.display();
    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 18:36
     * @Description:   充值余额
     * @Version: 1.0
     */
    @ResponseBody
    @RequestMapping("saveBalance")
    public Map saveBalance(@RequestParam Map incrementLog){
        try {
            //获取添加人信息
            AdminInfo adminInfo = this.getAdminInfo();
            Map reCode = incrementAccountsService.saveBalance(adminInfo, incrementLog);
            if("1".equals(reCode.get("reCode"))){
                return message(String.valueOf(reCode.get("msg")),1);
            }
            return message(String.valueOf(reCode.get("msg")), 0);
        } catch (Exception e) {
            printErrorMessage("充值余额:", e, this.request);
            return message("充值余额异常", 1);
        }
    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 20:20
     * @Description:   跳转到开通增值账户
     * @Version: 1.0
     */
    @RequestMapping("addAccounts")
    public String addAccounts(@RequestParam Map agentInfo){
        try {

            Map result = agentInfoService.getAgentOne(agentInfo);
            this.setAttribute("incrementAccounts", result);
        } catch (Exception e) {
            printErrorMessage("跳转到开通增值账户:", e, this.request);
        }
        return this.display();
    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 20:58
     * @Description:   开通增值账户
     * @Version: 1.0
     */
    @ResponseBody
    @RequestMapping("saveAccounts")
    public Map saveAccounts(@RequestParam Map incrementAccounts){
        try {
            incrementAccountsService.saveAccounts(incrementAccounts);
            return message("开户成功", 0);
        }catch (Exception e){
            printErrorMessage("开通增值账户:", e, this.request);
            return message("开户失败", 0);
        }
    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/16 14:14
     * @Description:   修改账户为通用
     * @Version: 1.0
     */
    @ResponseBody
    @RequestMapping("modifyAccounts")
    public Map modifyAccounts(@RequestParam Map incrementAccounts){
        try {
            incrementAccountsService.modifyAccounts(incrementAccounts);
            return message("修改成功", true);
        }catch (Exception e){
            printErrorMessage("修改账户为通用:", e, this.request);
            return message("修改失败", false);
        }
    }
}