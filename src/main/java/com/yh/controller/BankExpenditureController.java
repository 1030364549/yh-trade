package com.yh.controller;

import com.yh.entity.PropertiesData;
import com.yh.service.BankExpenditureService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 交易管理
 * @author puyiliang
 * @date 2019/7/12 11:52
 */
@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("BankExpenditure")
public class BankExpenditureController extends BaseController {

    @Resource
    private BankExpenditureService bankExpenditureService;

    /**
     * 交易信息列表查询
     * @return String
     * @author puyiliang
     * @date 2019/7/12 13:44
     */
    @RequiresPermissions("BankExpenditure_list")
    @RequestMapping("list")
    public String list(){
        try {
            if(isSearch()){
                Integer obj_no = this.getAdminInfo().getObj_no();
                if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                    this.getPage().getParams().put("obj_no",obj_no);
                }
                bankExpenditureService.getPageList("getPageList", this.getPage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("交易管理:",e,this.request);
        }
        return this.display();
    }

    /**
     * 交易信息导出Excel
     * @return String
     * @author puyiliang
     * @date 2019/7/12 13:44
     */
    @RequestMapping("exportExcel")
    public void exportExcel(@RequestParam Map param){
        try {
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                param.put("obj_no",obj_no);
            }
            bankExpenditureService.getExportExcelList(param, this.response);
        } catch (Exception e) {
            printErrorMessage("交易管理:",e,this.request);
        }
    }

    /**
     * 交易统计
     * @return String
     * @author puyiliang
     * @date 2019/7/12 13:44
     */
    @RequestMapping("statistics")
    public String statistics(){
        try {
            if(isSearch()){
                Integer obj_no = this.getAdminInfo().getObj_no();
                if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                    this.getPage().getParams().put("obj_no",obj_no);
                }
                bankExpenditureService.getPageList("statistics",this.getPage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("交易管理:",e,this.request);
        }
        return this.display();
    }

    /**
     * 交易统计信息导出Excel
     * @return String
     * @author puyiliang
     * @date 2019/7/12 13:44
     */
    @RequestMapping("exportStatisticsExcel")
    public void exportStatisticsExcel(@RequestParam Map param){
        try {
            Integer obj_no = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
                param.put("obj_no",obj_no);
            }
            bankExpenditureService.getStatisticsExportExcelList(param, this.response);
        } catch (Exception e) {
            printErrorMessage("交易管理:",e,this.request);
        }
    }

}
