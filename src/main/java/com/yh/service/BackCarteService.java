package com.yh.service;

import com.yh.entity.AdminInfo;
import com.yh.entity.BackCarte;
import com.yh.entity.Buttons;

import java.util.List;
import java.util.Map;
/**
 * ************************************
 * 菜单Service
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public interface BackCarteService extends BaseService<BackCarte, Integer> {
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] backCarteByUser <br>
	 * [描述] 获取用户菜单 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] List <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public List<Map<String, Object>> backCarteByUser(AdminInfo adminInfo) throws Exception;
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
	public Map getCartInfo(String id) throws Exception;
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] saveCarte <br>
	 * [描述] 添加菜单 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] void <br>
	 * [时间] 2018年10月24日 上午11:40:19 <br>
	 *********************************************************.<br>
	 */
	public void saveCarte(BackCarte bc) throws Exception;
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] delCarte <br>
	 * [描述] 删除菜单 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] void <br>
	 * [时间] 2018年10月24日 下午5:52:17 <br>
	 *********************************************************.<br>
	 */
	public void delCarte(Integer id) throws Exception;
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] buttonList <br>
	 * [描述] 查询指定菜单下的按钮列表 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] List<Map<String,Object>> <br>
	 * [时间] 2018年10月26日 下午5:40:20 <br>
	 *********************************************************.<br>
	 */
	public List<Map<String,Object>> buttonList(Integer carte_id) throws Exception;
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getOneButton <br>
	 * [描述] 获取按钮信息 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Buttons <br>
	 * [时间] 2018年10月29日 上午11:20:55 <br>
	 *********************************************************.<br>
	 */
	public Map<String,Object> getOneButton(Integer carte_id, Integer bt_id) throws Exception;
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] delButton <br>
	 * [描述] 删除按钮 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] void <br>
	 * [时间] 2018年10月29日 下午3:54:35 <br>
	 *********************************************************.<br>
	 */
	public void delButton(Integer carte_id, Integer bt_id) throws Exception;

	/**
	 *
	 *********************************************************.<br>
	 * [方法] saveButton <br>
	 * [描述] 添加按钮 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] void <br>
	 * [时间] 2018年10月29日 上午10:50:25 <br>
	 *********************************************************.<br>
	 */
	public void saveOrUpdateButton(Buttons bt) throws Exception;

	public List<Map<String,Object>> getList(String statement, Map map) throws Exception;
}
