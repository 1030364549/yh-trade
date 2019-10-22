package com.yh.aopshiro;

import com.yh.entity.AdminInfo;
import com.yh.entity.PropertiesData;
import com.yh.service.AdminInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.*;

/**
 * Realm的配置
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private AdminInfoService adminInfoService;

    /**
     * 设置授权信息==验证权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始授权(doGetAuthorizationInfo)");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //这里是写的demo，后面在实际项目中要通过这个登录的账号去获取用户的角色和权限，这里直接是写死的
        AdminInfo adminInfo = (AdminInfo) principals.getPrimaryPrincipal();

        //受理权限
        //角色
        //Set<String> roles = new HashSet<String>();
        //roles.add("role1");
        //authorizationInfo.setRoles(roles);
        //权限
        Map paramsMap=new HashMap<>();
        paramsMap.put("belong_terrace",PropertiesData.belong);
        paramsMap.put("gn_type1",1);
        paramsMap.put("gn_type0",0);
        paramsMap.put("admin_id",adminInfo.getAdmin_id());
        paramsMap.put("is_admin",adminInfo.getIs_admin());
        List<Map<String,Object>> shList=new ArrayList<>();
        try {
            shList=adminInfoService.getList("AdminInfo.getShiroList",paramsMap);
        } catch (Exception e) {
        }

        Set<String> permissions = new HashSet<String>();
        for (Map<String,Object> tmp : shList){
            String tmpStr=tmp.get("REL").toString();
            if (!"#".equals(tmpStr)&&!"*".equals(tmpStr)){
                permissions.add(tmpStr);
            }
        }
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
    /**
     * 设置认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            System.out.println("开始认证(doGetAuthenticationInfo)");
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            //获取用户输入的账号
            String userName = (String)token.getPrincipal();
            //通过userName去数据库中匹配用户信息，通过查询用户的情况做下面的处理
            //访问数据库
            AdminInfo adminInfo=adminInfoService.loginUser(userName);
            if (adminInfo==null){
                return null;
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    adminInfo, //用户名
                    adminInfo.getAdmin_pass(), //密码，写死
                    ByteSource.Util.bytes(userName+"salt"),//salt=username+salt
                    getName()  //realm name
            );
            return authenticationInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //清空shiro缓存的权限
    public void clearAuthz(){
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }


}
