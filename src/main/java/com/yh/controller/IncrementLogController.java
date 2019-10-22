package com.yh.controller;

import com.yh.entity.PropertiesData;
import com.yh.service.IncrementLogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: Liyh
 * @Date: 2019/10/15 8:20
 * @Description:   增值服务记录表
 * @Version: 1.0
 */
@Scope("prototype")
@Controller
@RequestMapping("IncrementLog")
public class IncrementLogController extends BaseController {
    @Resource
    private IncrementLogService incrementLogService;

    /**
     * @Author: Liyh
     * @Date: 2019/10/15 8:21
     * @Description:   查询记录列表
     * @Version: 1.0
     */
    @RequestMapping("list")
    public String list() {
        try {
            Integer objNo = this.getAdminInfo().getObj_no();
            if (!PropertiesData.AGENT_NUM.equals(String.valueOf(objNo))) {
                this.getPage().getParams().put("obj_no", objNo);
            }
            incrementLogService.getPageList(this.getPage());
        } catch (Exception e) {
            printErrorMessage("查看增值服务记录表:", e, this.request);
        }
        System.out.println("view0------"+this.display());
        return this.display();
    }

    /**
     *
     * 导出充值记录Excel表
     * @param param
     */
    @RequestMapping("exportExcel")
    public void exportExcel(@RequestParam Map param) {
        try {
            Integer objNo = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(objNo))){
                param.put("obj_no",objNo);
            }
            incrementLogService.getExportExcelList(param, this.response);
        }catch (Exception e){
            printErrorMessage("导出报表-充值记录", e, this.request);
        }
    }

    /*增值服务统计*/
    @RequestMapping("statistics")
    public String statistics() {
        /*判断是否来自网页查询，pageForm.ftl中的参数showType*/
        System.out.println(getShowType());
        if(isSearch()){
            try {
                Integer objNo = this.getAdminInfo().getObj_no();
                if (!PropertiesData.AGENT_NUM.equals(String.valueOf(objNo))) {
                    this.getPage().getParams().put("obj_no", objNo);
                }
                incrementLogService.getPageList("getPageListGrouping",this.getPage());
            } catch (Exception e) {
                printErrorMessage("查看增值服务统计表:", e, this.request);
            }
        }
        String display = this.display();
        System.out.println("view0------"+display);
        return display;
    }
    /*增值服务统计Excel导出*/
    @RequestMapping("exportExcelStatistics")
    public void exportExcelStatistics(@RequestParam Map param) {
        try {
            Integer objNo = this.getAdminInfo().getObj_no();
            if(!PropertiesData.AGENT_NUM.equals(String.valueOf(objNo))){
                param.put("obj_no",objNo);
            }
            incrementLogService.getExportExcelListStatistics(param, this.response);
        }catch (Exception e){
            printErrorMessage("导出报表-充值记录", e, this.request);
        }
    }

}
