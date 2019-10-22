package com.yh.controller;

import com.yh.entity.Page;
import com.yh.entity.PropertiesData;
import com.yh.service.TerminalService;
import com.yh.util.Utils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("Terminal")
public class TerminalController extends BaseController{
	@Resource
	private TerminalService terminalService;
	/**
	 *
	 *********************************************************.<br>
	 * [方法] getTerminalList <br>
	 * [描述] 终端信息查询 <br>
	 * [参数] [](对参数的描述) <br>
	 * [返回] java.lang.String <br>
	 * [日期] 2019/7/9
	 * [时间] 10:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@RequiresPermissions("Terminal_getTerminalList")
	@RequestMapping("getTerminalList")
	public String getTerminalList() {
		try {
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
				this.getPage().getParams().put("obj_no",obj_no);
			}
			terminalService.getPageList(this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("终端管理-终端信息查询:", e,this.request);
		}
		return this.display();
	}

	/**
	 * exportExcel 导出excel
	 *
	 * @param param
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(@RequestParam Map param) {
		try {
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
				param.put("obj_no",obj_no);
			}
			terminalService.getExportExcelList(param, this.response);
		}catch (Exception e){
			printErrorMessage("导出报表-终端信息", e, this.request);
		}
	}
}
