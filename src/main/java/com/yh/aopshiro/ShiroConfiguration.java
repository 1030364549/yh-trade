package com.yh.aopshiro;


import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 */
@Configuration
public class ShiroConfiguration {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("进入shiroFilter......");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置不需要拦截的路径
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //按顺序依次判断
        filterChainDefinitionMap.put("/public/**", "anon");
        filterChainDefinitionMap.put("/AdminInfo/getRandomImg", "anon");//验证码
        filterChainDefinitionMap.put("/AdminInfo/loginUser", "anon");//登录方法
        filterChainDefinitionMap.put("/AdminInfo/publicShowButton", "anon");//按钮校验
        filterChainDefinitionMap.put("/AdminInfo/goToLogin", "anon");//
        filterChainDefinitionMap.put("/AdminInfo/goLogin", "anon");//
        filterChainDefinitionMap.put("/signpos/getSign", "anon");//

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/AdminInfo/destroyLogin", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/webview/**", "authc");

        /************************************初始化所有的权限信息开始******************************************/
        //这里，如果以后再项目中使用的话，直接从数据库中查询
        //filterChainDefinitionMap.put("/user/list", "authc,perms[user:list]");
        /***************************************初始化所有的权限信息开始结束*********************************************/
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/AdminInfo/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/Index/index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/AdminInfo/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //后面这里可以设置缓存的机制
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
