package com.yh.service.impl;

import com.yh.dao.BackCarteDao;
import com.yh.entity.AdminInfo;
import com.yh.entity.BackCarte;
import com.yh.entity.Buttons;
import com.yh.service.BackCarteService;
import com.yh.util.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class BackCarteServiceImpl extends BaseServiceImpl<BackCarte, Integer>
		implements BackCarteService {
	@Resource
	private BackCarteDao backCarteDao;
	
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
	public List<Map<String, Object>> backCarteByUser(AdminInfo adminInfo)
		throws Exception {
		List<Map<String, Object>> backCarteList=new ArrayList<Map<String, Object>>();
		try {
			//用户ID
			Integer admin_id=adminInfo.getAdmin_id();
			
			Map map=new HashMap();
			map.put("admin_id", admin_id);
			map.put("belong_terrace", 2);
			map.put("is_admin", adminInfo.getIs_admin());
			map.put("gn_type",0);
			backCarteList=backCarteDao.getList("HFBackCarte", map);
		} catch (Exception e) {
			throw e;
		}
		return backCarteList;
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
	public Map getCartInfo(String id) throws Exception{
		Map map=new HashMap();
		try {
			map.put("id", id);
			map.put("belong_terrace", 2);
			map=(Map) backCarteDao.selectOne("getCartInfoById", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}
	
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
	public void saveCarte(BackCarte bc) throws Exception{
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			//级别
			int levels=1;
			//父类菜单
			String parents=bc.getParents();
			//如果当前选择的不是根节点,需要根据选择的值判断添加的菜单属于几级菜单
			//levels会跟着变动,parent_id值也将跟着变动
			map.put("carte_name", bc.getCarte_name());
			map.put("carte_intro", bc.getCarte_intro());
			map.put("target", bc.getTarget());
			map.put("href", bc.getHref());
			map.put("rel", bc.getRel());
			map.put("id", bc.getId());
			map.put("levels", levels);
			map.put("belong_terrace", 2);
			map.put("parents", parents);
			String xmaxP="";
			if ("0".equals(parents)) {
				//获取该菜单下最大菜单编号
				xmaxP=backCarteDao.selectOne("selMaxParentId", map).toString();
			}else{
				//计算当前菜单的级别
				levels=backCarteDao.getInt("selLevels", map)+1;
				//查询指定菜单下的最大节点
				Object omaxP=backCarteDao.selectOne("selMaxParentIdByPa", map);
				xmaxP=omaxP==null?parents:omaxP.toString();
			}
			String parent_id=getParentId(xmaxP,levels);
			map.put("parent_id", parent_id);
			map.put("levels", levels);
			if (bc.getId()==null) {
				//添加
				backCarteDao.insert("saveCarte",map);
			}else{
				//修改
				backCarteDao.update("updateCarte", map);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getParentId <br>
	 * [描述] 生成当前菜单的菜单编号 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2018年10月24日 下午4:00:41 <br>
	 *********************************************************.<br>
	 */
	public String getParentId(String xparents,int levels){
		String parent_id="";
		String res=Integer.parseInt(xparents.substring(levels*2-2, levels*2))+1+"";
		res=res.length()==2?res:"0"+res;
		parent_id=xparents.substring(0, levels*2-2)+res+xparents.substring(levels*2);
		return parent_id;
	}
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
	public void delCarte(Integer id) throws Exception{
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id", id);
			backCarteDao.delete("delCarte", map);
		} catch (Exception e) {
			throw e;
		}
	}
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
	public List<Map<String,Object>> buttonList(Integer carte_id) throws Exception{
		List<Map<String,Object>> btList=new ArrayList<Map<String,Object>>();
		try {
			btList=backCarteDao.getList("getButtonListByCarteId", carte_id);
		} catch (Exception e) {
			throw e;
		}
		return btList;
	}
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
	public Map<String,Object> getOneButton(Integer carte_id,Integer bt_id) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			map.put("carte_id", carte_id);
			map.put("bt_id", bt_id);
			map=(Map<String, Object>) backCarteDao.selectOne("getOneButtonById", map);
		} catch (Exception e) {
			throw e;
		}
		return map;
	}
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
	public void delButton(Integer carte_id,Integer bt_id) throws Exception{
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("carte_id", carte_id);
			map.put("bt_id", bt_id);
			backCarteDao.delete("delButton", map);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 *
	 *********************************************************.<br>
	 * [方法] saveButton <br>
	 * [描述] 添加/修改按钮 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] void <br>
	 * [时间] 2018年10月29日 上午10:50:25 <br>
	 *********************************************************.<br>
	 */
	public void saveOrUpdateButton(Buttons bt) throws Exception{
		try {
			bt.setAddtime(Utils.formateDate(2));
			bt.setRel(bt.getBt_href().replace("/", "_"));
			if (bt.getBt_id()==null) {
				//添加
				backCarteDao.insert("saveButton", bt);
			}else{
				//修改
				backCarteDao.update("updateButton", bt);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	//获取list
	public List<Map<String,Object>> getList(String statement, Map map){

		return backCarteDao.getList(statement,map);
	}

	/*******************************引用Dao**********************************/
	@Resource
	public void setBackCarteDao(BackCarteDao backCarteDao){
		super.setBaseDao(backCarteDao);
	}
	
}
