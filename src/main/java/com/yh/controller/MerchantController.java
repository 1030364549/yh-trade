package com.yh.controller;

import com.yh.entity.PropertiesData;
import com.yh.service.AreaService;
import com.yh.service.MerchantService;
import com.yh.util.Utils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("Merchant")
public class MerchantController extends BaseController{
	@Resource
	private MerchantService marchantService;
	@Resource
	private AreaService areaService;

	/**
	 *
	 *********************************************************.<br>
	 * [方法] getMerchantList <br>
	 * [描述] 商户查询 <br>
	 * [参数] [](对参数的描述) <br>
	 * [返回] java.lang.String <br>
	 * [日期] 2019/7/8
	 * [时间] 11:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@RequestMapping("getMerchantList")
	public String getMerchantList() {
		try {
			if (isSearch()){
				//添加当前登陆人机构号
				Integer obj_no = this.getAdminInfo().getObj_no();
				if(!PropertiesData.AGENT_NUM.equals(obj_no)){
					this.getPage().getParams().put("obj_no",obj_no);
				}
				marchantService.getMerchantList(this.getPage());
			}
		} catch (Exception e) {
			printErrorMessage("商户管理-商户信息查询:", e,this.request);
		}
		return this.display();
	}

	/**
	 *
	 *********************************************************.<br>
	 * [方法] getMerchantList <br>
	 * [描述] 变更商户状态 <br>
	 * [参数] [String id](对参数的描述) <br>
	 * [返回] Map <br>
	 * [日期] 2019/7/8
	 * [时间] 14:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@RequestMapping("change")
	public @ResponseBody Map change(@RequestParam Map marchant) {
		try {
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(obj_no)){
				marchant.put("obj_no",obj_no);
			}
			marchantService.change(marchant);
		} catch (Exception e) {
			printErrorMessage("更改用户状态失败！",e,this.request);
			return message("更改用户状态异常！", false);
		}
		return message("更改用户状态成功！", true);
	}

	/**
	 *
	 *********************************************************.<br>
	 * [方法] getSettlement <br>
	 * [描述] 商户结算信息查询 <br>
	 * [参数] [String id](对参数的描述) <br>
	 * [返回] String <br>
	 * [日期] 2019/7/8
	 * [时间] 17:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@RequestMapping("getSettlement")
	public String getSettlement(@RequestParam Map marchant) {
		try {
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(obj_no)){
				marchant.put("obj_no",obj_no);
			}
			//商户结算信息查询
			Map<String,Object > settlementMap = marchantService.getSettlement(marchant);
			this.setAttribute("settlementMap", settlementMap);
		} catch (Exception e) {
			printErrorMessage("商户结算信息查询异常:", e,this.request);
		}
		return this.display();
	}

	/**
	 * 商户工商信息查询
	 * @param marchant
	 * @return String
	 */
	@RequestMapping("getMerchantSchedule")
	public String getMerchantSchedule(@RequestParam Map marchant){
		try {
			//验证顶级代理商
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(obj_no)){
				marchant.put("obj_no",obj_no);
			}
			//商户工商信息查询
			Map<String,Object > merchantSchedule = marchantService.getMerchantSchedule(marchant);
			this.setAttribute("merchantSchedule", merchantSchedule);
		} catch (Exception e) {
			printErrorMessage("商户工商信息查询异常:", e,this.request);
		}
		return this.display();
	}

	/**
	 * 商户图片压缩包下载
	 * @param marchant
	 * @return String
	 */
	@RequestMapping("downloadImg")
	public void downloadImg(@RequestParam Map marchant, HttpServletResponse response){
		try {
			//验证顶级代理商
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(obj_no)){
				marchant.put("obj_no",obj_no);
			}
			//商户图片压缩包下载
			marchantService.downloadImg(marchant,response);
		} catch (Exception e) {
			printErrorMessage("商户图片压缩包下载异常:", e,this.request);
		}
	}
}
