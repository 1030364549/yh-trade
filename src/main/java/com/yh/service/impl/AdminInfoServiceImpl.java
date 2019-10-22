package com.yh.service.impl;

import com.yh.cache.RedisCached;
import com.yh.dao.AdminInfoDao;
import com.yh.entity.AdminInfo;
import com.yh.entity.Page;
import com.yh.entity.PropertiesData;
import com.yh.service.AdminInfoService;
import com.yh.util.DateUtil;
import com.yh.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class AdminInfoServiceImpl extends BaseServiceImpl<AdminInfo, Integer> implements
		AdminInfoService {
	@Resource
	private AdminInfoDao adminInfoDao;

	/**
	 * 
	 *********************************************************.<br>
	 * [方法] loginUser <br>
	 * [描述] 用户登录 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public AdminInfo loginUser(String admin_name) throws Exception {
		AdminInfo adminInfo=null;
		try {
			//根据用户名查询用户信息
			Map<String,String> map=new HashMap<String,String>();
			map.put("admin_name", admin_name);
			map.put("belong", "3");
			adminInfo=adminInfoDao.getOne("HFAdminLogin",map);
		} catch (Exception e) {
			throw e;
		}
		return adminInfo;
	}
	

	/**
	 * 
	 *********************************************************.<br>
	 * [方法] publicShowButton <br>
	 * [描述] 按钮权限校验 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2018年11月5日 下午7:18:28 <br>
	 *********************************************************.<br>
	 */
	public String publicShowButton(String rel,String carteId,String admin_name) throws Exception{
		try {
			String result="";
			Map<String,Object> btResultMap=(Map<String, Object>) RedisCached.getInstance().get(admin_name+PropertiesData.belong+"Buttons");
			if (btResultMap!=null) {
				//获取菜单下的按钮列表
				String keyStr=admin_name+carteId;
				List<Map<String, Object>> tmpList=(List<Map<String, Object>>) btResultMap.get(keyStr);
				if (tmpList==null || tmpList.size()==0){
					return result;
				}
				for (Map<String, Object> map : tmpList) {
					if (rel.equals(map.get("REL"))) {
						result=map.get("BT_ID").toString();
						return result;
					}
				}
			}
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	//获取list
	public List<Map<String,Object>> getList(String statement, Object admin_id){

		return adminInfoDao.getList(statement,admin_id);
	}
	/**
	 *
	 *********************************************************.<br>
	 * [方法] getOneObject <br>
	 * [描述] 获取单个对象 <br>
	 * [参数] (对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public Object getOneObject(String statement,Integer param) throws Exception{
		return adminInfoDao.selectOne(statement,param);
	}
	/**
	 *
	 *********************************************************.<br>
	 * [方法] updateUserPass <br>
	 * [描述] 修改密码 <br>
	 * [参数] (对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public int updateUserPass(Map paramMap) throws Exception{
		int result=0;
		try {
			Integer admin_id = Integer.parseInt(paramMap.get("admin_id").toString());
			//查询数据库
			AdminInfo adminInfo = adminInfoDao.getOne("AdminInfo.getAdminInfoById",admin_id);
			String oldPass=SecurityUtil.sha1Encode(paramMap.get("oldadmin_pass").toString());
			if (!adminInfo.getAdmin_pass().equals(oldPass)){
				return -1;//旧密码不对
			}
			paramMap.put("admin_pass",SecurityUtil.sha1Encode(paramMap.get("admin_pass").toString()));
			result=adminInfoDao.update("AdminInfo.updateAdminPass",paramMap);
		} catch (NumberFormatException e) {
			throw e;
		}
		return result;
	}

	/**
	 * @author
	 * @param page
	 */
	@Override
	public void getPageList(Page page) {
		adminInfoDao.getPageList(page);
	}

	@Override
	public void updateAdminAtt(Map adminInfo) throws Exception {
		adminInfoDao.update("updateAdminAtt",adminInfo);
	}

	@Override
	public void updateAdminPassword(Map adminInfo) throws Exception{
		adminInfoDao.update("updateAdminPassword",adminInfo);
	}

	@Override
	public int userCheckName(Map adminInfo) throws Exception {

		//默认存在
		return adminInfoDao.getCount("getCountByName",adminInfo);
	}

	@Override
	public void saveAdminInfo(Map adminInfo, AdminInfo admin)  throws Exception{
		adminInfo.put("add_man",admin.getAdmin_name());
		adminInfo.put("add_time",DateUtil.get4yMd(new Date()));
		adminInfoDao.insert("saveAdminInfo",adminInfo);
	}

	@Override
	public Map getAdminByAgentInfo(Map agentinfo) {
		List<Map> list = adminInfoDao.getObjectList("getAdminByAgentInfo",agentinfo);
		if (list != null && list.size() >0){
			StringBuffer sb = new StringBuffer();
			for (int i =0; i<list.size() && i < 3; i++){
				sb.append(list.get(i).get("ADMIN_NAME")).append(",");
			}
			if (list.size() > 3){
				sb.append("...");
			}else{
				sb.substring(0,sb.length()-1);
			}
			agentinfo.put("admin",sb.toString());
		}
		return agentinfo;
	}

}
