package com.yh.listener;
import com.yh.config.PropertiesListenerConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 配置监听器
 *
 * @作者 zyy
 **/
@WebListener
public class PropertiesListener implements ServletContextListener {

    private String propertyFileName;

    public PropertiesListener(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    public PropertiesListener() {
        //针对打包的时候  tomcat会条用无参构造函数
        this.propertyFileName ="message.yml";
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            PropertiesListenerConfig.loadAllProperties(propertyFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
