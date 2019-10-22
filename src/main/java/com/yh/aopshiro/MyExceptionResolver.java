package com.yh.aopshiro;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统一异常处理类
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    /**
     * 异常处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
           HttpServletResponse httpServletResponse, Object o, Exception e) {

        //处理未经授权的异常
        if (e instanceof UnauthorizedException){
            //判断是Ajax请求还是普通页面请求
            if (isAjax(httpServletRequest)){
                if (ckResponseBody(httpServletRequest,o)){
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    PrintWriter writer = null;
                    try {
                        writer = httpServletResponse.getWriter();
                    } catch (IOException e1) {
                    }
                    //具体操作
                    Map<String,String> resMap=new HashMap<>();
                    resMap.put("statusCode","300");
                    resMap.put("message","403,对不起，您无权访问!");
                    writer.write(JSON.toJSONString(resMap));
                    writer.flush();
                    writer.close();
                }else{
                    ModelAndView mv = new ModelAndView("/AdminInfo/403");
                    return mv;
                }
            }else{
                ModelAndView mv = new ModelAndView("/AdminInfo/403");
                return mv;
            }
        }else{
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            try {
                writer = httpServletResponse.getWriter();
            } catch (IOException e1) {
            }
            //具体操作
            Map<String,String> resMap=new HashMap<>();
            resMap.put("statusCode","300");
            resMap.put("message","系统异常!");
            writer.write(JSON.toJSONString(resMap));
            writer.flush();
            writer.close();
        }
        return null;
    }

    /**
     * 判断是否是ajax请求
     * @param httpRequest
     * @return
     */
    public boolean isAjax(HttpServletRequest httpRequest){
        return (httpRequest.getHeader("X-Requested-With") != null
                &&
                "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
    }

    public boolean ckResponseBody(HttpServletRequest httpServletRequest,Object o){
        boolean flag=false;
        try {
            Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
            Matcher matcher = pattern.matcher(o.toString());

            List<String> clsStr=new ArrayList<>();
            String tmpStr="";
            while(matcher.find()){
                tmpStr=matcher.group();
            }
            Class[] paramClas=null;
            if (!"".equals(tmpStr)){
                String[] paramStr=tmpStr.split(",");
                paramClas=new Class[paramStr.length];
                for (int i=0;i<paramStr.length;i++){

                    String[] stra=paramStr[i].split("\\.");
                    String claName=stra[stra.length-1];
                    paramClas[i]=getClass(claName);
                }
            }

            //获取方法名
            String uri=httpServletRequest.getRequestURI();
            String[] uris=uri.split("\\/");

            //获取类名
            String className="com.yh.controller."+uris[uris.length-2]+"Controller";
            Class<?> classz=Class.forName(className);


            String methStr=uris[uris.length-1];
            Method method=null;
            if (paramClas==null){
                method=classz.getDeclaredMethod(methStr);
            }else{
                method=classz.getDeclaredMethod(methStr,paramClas);
            }
            if ( method != null ) {
                // 获取方法中的注解
                Annotation[] ans = method.getAnnotations();
                for( int i = 0;i < ans.length;i++) {
                    if ("ResponseBody".equals(ans[i].annotationType().getSimpleName())){
                        return true;
                    }
                    //System.out.println("method testMethod annotation:"+ans[i].annotationType().getSimpleName());
                }
            }
        } catch (Exception e) {
        }
        return flag;
    }

    public Class getClass(String clasName){
        Class c=null;
        switch (clasName){
            case "Integer":
                c=Integer.class;
                break;
            case "String":
                c=String.class;
                break;
            case "Map":
                c=Map.class;
                break;
        }
        return c;
    }


}
