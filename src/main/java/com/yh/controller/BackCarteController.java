package com.yh.controller;

import com.yh.aopshiro.BlueLog;
import com.yh.entity.BackCarte;
import com.yh.entity.Buttons;
import com.yh.service.BackCarteService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ************************************
 * 用户控制层
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("BackCarte")
public class BackCarteController extends BaseController {
	@Resource
	private BackCarteService backCarteService;

	/**
	 * 
	 *********************************************************.<br>
	 * [方法] list <br>
	 * [描述] 菜单<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
//	@RequiresPermissions("BackCarte_list")
	@RequestMapping("list")
	public String list(){
		try {
			List<Map<String, Object>> carteList=backCarteService.backCarteByUser(getAdminInfo());
			setAttribute("carteList", carteList);
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("菜单管理:",e,this.request);
		}
		return this.display();
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getCartInfo <br>
	 * [描述] 查看菜单详情<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Map <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("getCartInfo")
	public @ResponseBody Map getCartInfo(@RequestParam String id){
		System.out.println("ID:"+id);
		Map resultMap=new HashMap();
		try {
			resultMap=backCarteService.getCartInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("菜单详情:",e,this.request);
		}
		return resultMap;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] saveCarte <br>
	 * [描述] 保存菜单信息<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Map <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@BlueLog("系统管理,菜单管理,保存菜单")//模块名称,菜单名称,功能名称
//	@RequiresPermissions("BackCarte_saveCarte")
	@RequestMapping("saveCarte")
	public @ResponseBody Map saveCarte(BackCarte bc){
		try {
			backCarteService.saveCarte(bc);
			return message("操作成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("添加/修改菜单:",e,this.request);
			return message("异常", false);
		}
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] delCarte <br>
	 * [描述] 删除指定菜单 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Map <br>
	 * [时间] 2018年10月24日 下午5:51:04 <br>
	 *********************************************************.<br>
	 */
	@BlueLog("系统管理,菜单管理,删除菜单")//模块名称,菜单名称,功能名称
//	@RequiresPermissions("BackCarte_delCarte")
	@RequestMapping("delCarte")
	public @ResponseBody Map delCarte(@RequestParam Integer id){
		try {
			backCarteService.delCarte(id);
			return message("删除成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("删除改菜单:",e,this.request);
			return message("异常", false);
		}
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] buttonList <br>
	 * [描述] 按钮列表 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2018年10月26日 下午5:36:46 <br>
	 *********************************************************.<br>
	 */
	@RequestMapping("buttonList")
	public String buttonList(@RequestParam Integer carte_id){
		try {
			List<Map<String,Object>> btList=backCarteService.buttonList(carte_id);
			setAttribute("btList", btList);
			setAttribute("carte_id", carte_id);
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("按钮列表:",e,this.request);
		}
		return this.display();
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] addButton <br>
	 * [描述] 添加按钮 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2018年10月26日 下午6:02:53 <br>
	 *********************************************************.<br>
	 */
//	@RequiresPermissions("BackCarte_addButton")
	@RequestMapping("addButton")
	public String addButton(@RequestParam Integer carte_id,Integer bt_id){
		System.out.println(bt_id);
		try {
			if (bt_id!=null) {
				//查询
				Map<String,Object> btMap=backCarteService.getOneButton(carte_id, bt_id);
				setAttribute("bt", btMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAttribute("carte_id", carte_id);
		return this.display();
	}
	/**
	 *
	 *********************************************************.<br>
	 * [方法] saveOrUpdateButton <br>
	 * [描述] 添加/修改按钮 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Map <br>
	 * [时间] 2018年10月29日 上午10:44:45 <br>
	 *********************************************************.<br>
	 */
	@BlueLog("系统管理,菜单管理,保存按钮")//模块名称,菜单名称,功能名称
	@RequestMapping("saveOrUpdateButton")
	public @ResponseBody Map saveOrUpdateButton(Buttons bt){
		System.out.println(bt.toString());
		try {
			backCarteService.saveOrUpdateButton(bt);
			return Success("操作成功", "");
		} catch (Exception e) {
			printErrorMessage("添加/修改按钮:",e,this.request);
			return message("异常", 1);
		}
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] delButton <br>
	 * [描述] 删除按钮 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Map <br>
	 * [时间] 2018年10月29日 下午3:52:46 <br>
	 *********************************************************.<br>
	 */
	@BlueLog("系统管理,菜单管理,删除按钮")//模块名称,菜单名称,功能名称
//	@RequiresPermissions("BackCarte_delButton")
	@RequestMapping("delButton")
	public @ResponseBody Map delButton(@RequestParam Integer carte_id,@RequestParam Integer bt_id){
		try {
			backCarteService.delButton(carte_id, bt_id);
			return Success("删除成功", "");
		} catch (Exception e) {
			printErrorMessage("删除按钮:",e,this.request);
			return message("异常", 1);
		}
	}
	
	
}
