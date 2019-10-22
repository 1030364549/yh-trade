package com.yh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yh.entity.AdminInfo;
import com.yh.entity.Page;
/**
 * ************************************
 * 用户Service
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public interface AdminInfoService extends BaseService<AdminInfo, Integer> {
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
	public AdminInfo loginUser(String admin_name) throws Exception;

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
	public String publicShowButton(String rel, String carteId, String admin_name) throws Exception;

	public List<Map<String,Object>> getList(String statement, Object admin_id) throws Exception;

	/**
	 *
	 *********************************************************.<br>
	 * [方法] getOneObject <br>
	 * [描述] 获取单个对象 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public Object getOneObject(String statement,Integer param) throws Exception;
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
	public int updateUserPass(Map paramMap) throws Exception;

    void getPageList(Page page)  throws Exception;

	void updateAdminAtt(Map adminInfo)  throws Exception;

	void updateAdminPassword(Map adminInfo) throws Exception;

    int userCheckName(Map adminInfo) throws Exception;

    void saveAdminInfo(Map adminInfo, AdminInfo admin) throws Exception;

    Map getAdminByAgentInfo(Map agentinfo);
}
