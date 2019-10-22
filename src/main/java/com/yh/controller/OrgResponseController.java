package com.yh.controller;

import com.yh.entity.PropertiesData;
import com.yh.service.OrgResponseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("OrgResponse")
public class OrgResponseController extends BaseController{

    @Resource
    private OrgResponseService responseService;

    @RequiresPermissions("OrgResponse_list")
    @RequestMapping("list")
    public String list() {
        try {
            if(isSearch()){
                //添加当前登陆人机构号
                Integer obj_no = this.getAdminInfo().getObj_no();
                if(!PropertiesData.AGENT_NUM.equals(obj_no)){
                    this.getPage().getParams().put("obj_no",obj_no);
                }
                responseService.getPageList(this.getPage());
            }
        } catch (Exception e) {
            printErrorMessage("商户管理-商户信息查询:", e,this.request);
        }
        return this.display();
    }

}
