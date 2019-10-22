package com.yh.aopshiro;

import com.alibaba.fastjson.JSON;
import com.yh.entity.AdminInfo;
import com.yh.entity.PropertiesData;
import com.yh.service.SysManagerService;
import com.yh.util.IPUtils;
import com.yh.util.Utils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysManagerService sysManagerService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.yh.aopshiro.BlueLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        try {
            System.out.println("切面。。。。。");
            //保存日志
            Map sysLogMap=new HashMap<>();

            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();

            //获取操作
            BlueLog myLog = method.getAnnotation(BlueLog.class);
            String operation="";
            if (myLog != null) {
                operation = myLog.value();//保存获取的操作
            }
            String[] operations=operation.split(",");
            sysLogMap.put("operation",operations[2]);
            sysLogMap.put("modular",operations[0]);
            sysLogMap.put("cartename",operations[1]);

            //获取请求的类名
            //String className = joinPoint.getTarget().getClass().getName();
            String className = joinPoint.getTarget().getClass().getSimpleName();
            //获取请求的方法名
            String methodName = method.getName();
            sysLogMap.put("method",className + "." + methodName);

            //请求的参数
            Object[] args = joinPoint.getArgs();
            //将参数所在的数组转换成json
            String params = JSON.toJSONString(args);
            params=params==null?"":params;
            if (params.length()>2000){
                params="参数过长!";
            }
            sysLogMap.put("params",params);

            sysLogMap.put("createdate",Utils.formateDate(0));
            sysLogMap.put("createtime",Utils.formateDate(3));
            sysLogMap.put("belong",PropertiesData.belong);

            //获取用户ip地址
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //获取用户名
            AdminInfo adminInfo=(AdminInfo) request.getSession().getAttribute("HFBACKSTAGEUSER");
            sysLogMap.put("username",adminInfo.getAdmin_name());
            sysLogMap.put("ip",IPUtils.getIpAddr(request));

            //调用service保存SysLog实体类到数据库
            sysManagerService.saveSysLog(sysLogMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
