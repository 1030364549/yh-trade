package com.yh.controller;

import com.google.code.kaptcha.Constants;
import com.yh.cache.RedisCached;
import com.yh.entity.AdminInfo;
import com.yh.entity.PropertiesData;
import com.yh.entity.ReponseData;
import com.yh.service.AdminInfoService;
import com.yh.util.RandomCodeUtil;
import com.yh.util.SecurityUtil;
import com.yh.util.Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import java.awt.image.BufferedImage;
import java.util.HashMap;
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
@RequestMapping("AdminInfo")
public class AdminInfoController extends BaseController {
	@Resource
	private AdminInfoService adminInfoService;

	
	 /**
	 * 
	 *********************************************************.<br>
	 * [方法] login <br>
	 * [描述] 跳转到登录界面<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("login")
	public String login(){
		//获取登录的COOKIE信息
		Map<String,Cookie> cookieMap=Utils.readCookieMap(getRequest());
		if (cookieMap.get("hflogin_user")!=null) {
			this.setAttribute("login_user", cookieMap.get("hflogin_user").getValue());
			this.setAttribute("login_password", cookieMap.get("hflogin_password").getValue());
			this.setAttribute("remember", cookieMap.get("hfremember").getValue());
		}
		return this.display();
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] destroyLogin <br>
	 * [描述] 注销登录跳转到登录界面<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("destroyLogin")
	public String destroyLogin(){
		
		if (getAdminInfo()!=null) {
			//移除按钮缓存
			RedisCached.getInstance().delete(getAdminInfo().getAdmin_name()+PropertiesData.belong+"Buttons");
			RedisCached.getInstance().delete(getAdminInfo().getAdmin_name()+PropertiesData.belong+"menuCarte");
		}
		//SecurityUtils.getSubject().logout();
		
		getRequest().getSession().removeAttribute("HFBACKSTAGEUSER");
		//使 session 无效
		getRequest().getSession().invalidate();
		//获取登录的COOKIE信息
		Map<String,Cookie> cookieMap=Utils.readCookieMap(getRequest());
		if (cookieMap.get("hflogin_user")!=null) {
			this.setAttribute("login_user", cookieMap.get("hflogin_user").getValue());
			this.setAttribute("login_password", cookieMap.get("hflogin_password").getValue());
			this.setAttribute("remember", cookieMap.get("hfremember").getValue());
		}
		return "AdminInfo/login";
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] goToLogin <br>
	 * [描述] 超时提示信息<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Map<String,String> <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("goToLogin")
	public @ResponseBody Map<String,String> goToLogin(){
		this.getMes().put("statusCode", "300");
		this.getMes().put("message", "\u4f1a\u8bdd\u8d85\u65f6\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u3002");
		return this.getMes();
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] goLogin <br>
	 * [描述] 超时弹窗跳转到登录界面<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("goLogin")
	public String goLogin(){
		//获取登录的COOKIE信息
		Map<String,Cookie> cookieMap=Utils.readCookieMap(getRequest());
		if (cookieMap.get("hflogin_user")!=null) {
			this.setAttribute("login_user", cookieMap.get("hflogin_user").getValue());
			this.setAttribute("login_password", cookieMap.get("hflogin_password").getValue());
			this.setAttribute("remember", cookieMap.get("hfremember").getValue());
		}
		return this.display();
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getRandomImg <br>
	 * [描述] 获取验证码 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("getRandomImg")
	public String getRandomImg(){
		try {
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache"); 
			response.setDateHeader("Expires", 0); 
			response.setContentType("image/jpeg");
			//获取验证码的缓存图片
			BufferedImage buffImg=RandomCodeUtil.instance().initImage(request);
			//输出流
			ServletOutputStream outStram=response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", outStram);
			outStram.close();
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("获取验证码:",e,this.request);
		}
		return null;
	}
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
	@RequestMapping(value="/loginUser",method=RequestMethod.POST)
	public @ResponseBody Map loginUser(@RequestBody Map param){
		Map<String,String> result=new HashMap<String, String>();
		String resCode="E3";
		try {
			if (param!=null && !"".equals(param)) {
				//获取登录的COOKIE信息
				Map<String,Cookie> cookieMap=Utils.readCookieMap(getRequest());
				String hflogin_user=null;//COOKIE中的用户名
				String hflogin_password=null;//COOKIE中的用户密码
				if (cookieMap.get("hflogin_user")!=null) {
					hflogin_user=cookieMap.get("hflogin_user").getValue();
					hflogin_password=cookieMap.get("hflogin_password").getValue();
				}

				String admin_name=param.get("admin_name").toString().trim();
				String admin_password=param.get("admin_password").toString();
				boolean remember=Boolean.parseBoolean(param.get("remember").toString());
				
				Object isLogin=param.get("isLogin");

				RedisCached.getInstance().delete(admin_name+PropertiesData.belong+"menuCarte");
				
				if (admin_name.equals(hflogin_user) && admin_password.equals(hflogin_password)) {
					admin_password=hflogin_password;
				}else{
					admin_password=SecurityUtil.sha1Encode(admin_password);
					hflogin_password=admin_password;
					hflogin_user=admin_name;
				}
				//记住密码
				int maxAge=3600*24*365;
				String hfrememberStr="1";
				String adminName=hflogin_user;
				if (!remember) {
					//清空记忆
					hflogin_user=null;;
					hflogin_password=null;
					hfrememberStr="0";
				}
				Utils.addCookie(response, "hflogin_user", hflogin_user, maxAge);
				Utils.addCookie(response, "hflogin_password", hflogin_password, maxAge);
				Utils.addCookie(response, "hfremember", hfrememberStr, maxAge);
				Utils.addCookie(response, "adminName", adminName, maxAge);
				//验证 验证码
				boolean flag=true;
				if (isLogin==null) {
					String code=param.get("code").toString();
					String code_value=(String) getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
					code="1";
					code_value="1";
					if (!code.equalsIgnoreCase(code_value)) {
						resCode="E2";
						flag=false;
					}
				}
				//移除验证码--不管是否验证码通过
				getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
				//验证通过
				if (flag) {
					UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(admin_name,admin_password);
					Subject subject = SecurityUtils.getSubject();
					try {
						subject.login(usernamePasswordToken);   //完成登录
						AdminInfo adminInfo=(AdminInfo)subject.getPrincipal();
						getSession().setAttribute("HFBACKSTAGEUSER",adminInfo);
						resCode="E0";
					} catch (AuthenticationException e) {
						//账户不存在
						resCode="E3";
					}
				}
			}else{
				//数据错误
				resCode="E1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			resCode="E4";
			printErrorMessage("用户登录:",e,this.request);
		}finally{
			result.put("code", resCode);
			result.put("codeMsg", ReponseData.ajaxReq_ResCode.get(resCode));
			return result;
		}
	}
	

	/**
	 * 
	 *********************************************************.<br>
	 * [方法] publicShowButton <br>
	 * [描述] 按钮权限校验-是否显示按钮 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2018年11月5日 下午4:11:01 <br>
	 *********************************************************.<br>
	 */
	@RequestMapping("publicShowButton")
	public @ResponseBody String publicShowButton(@RequestParam String rel,@RequestParam String carte_id){
		AdminInfo loginAdmin=this.getAdminInfo();
		String result="false";
		try {
			if (loginAdmin.getIs_admin()==1) {
				result=adminInfoService.publicShowButton(rel, carte_id, loginAdmin.getAdmin_name());
				result="".equals(result)?"false":"true";
			}else{
				result="true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			printErrorMessage("钮权限校验:",e,this.request);
		}
		return result;
	}
	/**
	 *
	 *********************************************************.<br>
	 * [方法] error <br>
	 * [描述] 无权限页面<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("error")
	public String error(){
		return this.display();
	}

	/**
	 *
	 *********************************************************.<br>
	 * [方法] resetPass <br>
	 * [描述] 修改密码<br>
	 * [参数] (对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("resetPass")
	public String resetPass(@RequestParam Integer admin_id){
		AdminInfo adminInfo=null;
		try {
			adminInfo=(AdminInfo)adminInfoService.getOneObject("AdminInfo.getAdminInfoById",admin_id);
		} catch (Exception e) {
			printErrorMessage("修改密码界面:",e,this.request);
		}
		setAttribute("adminInfo", adminInfo);
		return this.display();
	}
	/**
	 *
	 *********************************************************.<br>
	 * [方法] updateUserPass <br>
	 * [描述] 修改密码<br>
	 * [参数] (对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	@RequestMapping("updateUserPass")
	public @ResponseBody Map updateUserPass(@RequestParam Map paramMap){
		try {
			//加密
			int result=adminInfoService.updateUserPass(paramMap);
			if (result==-1){
				return message("旧密码不对",1);
			}
			return result>0?message("修改成功,请重新登录!",0):message("修改失败",1);
		} catch (Exception e) {
			printErrorMessage("修改密码:",e,this.request);
			return message("异常",1);
		}
	}

	/**
	 * 机构用户管理列表
	 * @author puyiliang
	 * @return String
	 */
	@RequiresPermissions("AdminInfo_list")
	@RequestMapping("list")
	public String list(){
		try {
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
				this.getPage().getParams().put("obj_no",obj_no);
			}
			adminInfoService.getPageList(this.getPage());
		} catch (Exception e) {
			printErrorMessage("用户列表:",e,this.request);
		}
		return this.display();
	}

	/**
	 * 启用/禁用用户
	 * @author puyiliang
	 * @return String
	 */
	@RequestMapping("updateAdminAtt")
	@ResponseBody
	public Map updateAdminAtt(@RequestParam Map adminInfo){
		try {
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
				adminInfo.put("obj_no",obj_no);
			}
			adminInfoService.updateAdminAtt(adminInfo);
			return message("启用/禁用用户成功！", true);
		} catch (Exception e) {
			printErrorMessage("启用/禁用用户:",e,this.request);
		}
		return message("启用/禁用用户异常！", false);
	}

	/**
	 * 重置用户密码
	 * @author puyiliang
	 * @return String
	 */
	@RequestMapping("updateAdminPassword")
	@ResponseBody
	public Map updateAdminPassword(@RequestParam Map adminInfo){
		try {
			Integer obj_no = this.getAdminInfo().getObj_no();
			if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
				adminInfo.put("obj_no",obj_no);
			}
			adminInfoService.updateAdminPassword(adminInfo);
			return message("重置用户密码成功！", true);
		} catch (Exception e) {
			printErrorMessage("重置用户密码:",e,this.request);
		}
		return message("重置用户密码异常！", false);
	}
	/**
	 * 查询用户名唯一
	 * @author puyiliang
	 * @return String
	 */
	@RequestMapping("userCheckName")
	@ResponseBody
	public boolean userCheckName(@RequestParam String admin_name){
		boolean flag = true;
		try {
			Map admininfo =new HashMap(2);
			admininfo.put("admin_name",admin_name);
			admininfo.put("belong",3);
			int result = adminInfoService.userCheckName(admininfo);
			flag = result > 0 ? true : false;
		} catch (Exception e) {
			printErrorMessage("添加用户:",e,this.request);
		}
		return flag;
	}

	/**
	 * 配置登陆用户
	 * @author puyiliang
	 * @param agentinfo Map
	 * @return Map
	 */
	@RequestMapping("saveAdminInfo")
	@ResponseBody
	public Map saveAdminInfo(@RequestParam Map adminInfo){
		try {
			//获取登陆人信息
			AdminInfo admin = this.getAdminInfo();
			adminInfoService.saveAdminInfo(adminInfo, admin);
			return message("配置登陆用户成功！", 0);
		} catch (Exception e) {
			printErrorMessage("配置登陆用户成功:", e, this.request);
			return message("配置登陆用户异常", 1);
		}
	}
	/**
	 * 配置登陆用户跳转
	 * @author puyiliang
	 * @param agentinfo Map
	 * @return Map
	 */
	@RequestMapping("adminInfoAdd")
	public String adminInfoAdd(@RequestParam Map agentinfo){
		try {
			Map result = adminInfoService.getAdminByAgentInfo(agentinfo);
			this.setAttribute("agentinfo", result);
		} catch (Exception e) {
			printErrorMessage("配置登录用户跳转:", e, this.request);
		}
		return this.display();
	}
}
