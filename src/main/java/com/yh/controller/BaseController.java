package com.yh.controller;

import com.yh.cache.GetCacheMethod;
import com.yh.entity.AdminInfo;
import com.yh.entity.Page;
import com.yh.util.DateUtil;
import com.yh.util.ExceptionLog;
import com.yh.util.IPUtils;
import com.yh.util.Utils;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
@Scope("prototype")
@Transactional
public class BaseController {
    private Page page;
    protected Map<String, String> mes = new HashMap<String, String>();//消息
    protected String message;//记录操作结果信息测
    protected int resForFinally = 0;//0 成功、1-失败,前台返回成功或者失败
    protected boolean resFinally = true;//前台返回成功或者失败
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private StringBuffer msg = new StringBuffer();
    private static String newLine = "\r\n";
    private String showType;//为空 不查询数据，等于 1 时 才查询数据库
    ExceptionLog exLog4j2 = new ExceptionLog();//异常处理类

    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * ************************************
     * 返回当前调用方法的类名与方法名，做为VIEW的地址
     *
     * @param
     * @return current class/method
     * ************************************
     */
    public String display() {
        String[] class_str = this.getClass().getName().split("\\.");
        //方法名称
        String action = class_str[class_str.length - 1].replace("Controller", "");
        String method = new Exception().getStackTrace()[1].getMethodName();
        String base = this.getRequest().getContextPath();
        System.out.println("base------"+base);
        StringBuffer fullPath = new StringBuffer(this.getRequest().getScheme());
        fullPath.append("://").append(this.getRequest().getServerName());
        fullPath.append(":").append(this.getRequest().getServerPort()).append(base);
        this.setAttribute("base", base);
        String relativePath=base+"/"+action+"/"+method;
        this.setAttribute("baseClass",base+"/"+action);   //http://localhost/finance/action
        this.setAttribute("urls", relativePath);
        //注册freemaker 自定义函数
        this.setAttribute("getCache", new GetCacheMethod(response));
        if (this.page != null) {
            this.setAttribute("p", this.getPage());
        }
        //获取菜单ID
        Integer carte_id = 0;
        String carteStr = this.getParameter("carte_id");
        if ("null".equals(carteStr) || "".equals(carteStr) || carteStr == null) {
            Object carteO = this.getPage().getParams().get("carte_id");
            carteStr = (carteO == null ||carteO == ""? "0" : carteO.toString());
        }
        if (!"".equals(carteStr) && carteStr != null) {
            carte_id = Integer.parseInt(carteStr);
        }
        setAttribute("carte_id", carte_id);
        return action + "/" + method;
    }

    /**
     * ************************************
     * 返回当前调用方法的类名与方法名，做为VIEW的地址
     *
     * @param
     * @return current class/method
     * ************************************
     */
    public String display(String method) {
        String[] class_str = this.getClass().getName().split("\\.");
        //方法名称
        String action = class_str[class_str.length - 1].replace("Controller", "");
        String base = this.getRequest().getContextPath();
        StringBuffer fullPath = new StringBuffer(this.getRequest().getScheme());
        fullPath.append("://").append(this.getRequest().getServerName());
        fullPath.append(":").append(this.getRequest().getServerPort()).append(base);

        this.setAttribute("base", fullPath);
        this.setAttribute("baseClass", base + "/" + action);   //http://localhost/finance/action
        this.setAttribute("url", this.getRequest().getRequestURL().toString());
        //注册freemaker 自定义函数
        this.setAttribute("getCache", new GetCacheMethod(response));
        if (this.page != null) {
            this.setAttribute("p", this.page);
        }
        return method;
    }

    /**
     * ************************************
     * 显示成功信息
     *
     * @param
     * @return Map<String, String>
     * ************************************
     */
    public Map<String, String> Success(String message, String navTabId) {
        if ("".equals(message)) {
            message = "操作成功";
        }
        mes.put("message", message);
        mes.put("statusCode", "200");
        mes.put("navTabId", navTabId);
        return mes;
    }

    /**
     * ************************************
     * 返回信息给前端-不关闭当前页
     *
     * @param message
     * @param flag
     * @return Map<String, String>
     * ************************************
     */
    public Map<String, String> message(String message, boolean flag) {
        if(!flag){
            //事物手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        mes.put("statusCode", flag ? "200" : "300");
        mes.put("message", message);
        mes.put("tabid", "table");
        return mes;
    }

    public Map<String, String> message(String message, boolean flag, String name) {
        if(!flag){
            //事物手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        mes.put("statusCode", flag ? "200" : "300");
        mes.put("message", message);
        mes.put("tabid", name);
        mes.put("closeCurrent", "true");
        return mes;
    }

    /**
     * ************************************
     * 返回信息给前端并关闭当前窗口
     *
     * @param message
     * @param flag
     * @return Map<String, String>
     * ************************************
     */
    public Map<String, String> message(String message, int resultInt) {
        if(1==resultInt){
            //事物手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        mes.put("statusCode", resultInt == 0 ? "200" : "300");
        mes.put("message", message);
        mes.put("tabid", "table");
        mes.put("dialogid", "table");
        mes.put("closeCurrent", "true");
        return mes;
    }

    public Map<String, String> messageDialog(String message, int resultInt) {
        mes.put("statusCode", resultInt == 0 ? "200" : "300");
        mes.put("message", message);
        mes.put("dialogid", "table");
        mes.put("closeCurrent", "true");
        return mes;
    }

    public Map<String, String> messageinfo(String message, int resultInt, String merno, String nature, String newnature) {
        mes.put("statusCode", resultInt == 0 ? "200" : "300");
        mes.put("message", message);
        mes.put("tabid", "table");
        mes.put("merno", merno);
        mes.put("nature", nature);
        mes.put("newnature", newnature);
        mes.put("closeCurrent", "true");
        return mes;
    }

    /**
     * ************************************
     * 返回信息给前端-关闭当前窗口并刷新指定的窗口
     *
     * @param message
     * @param flag
     * @return Map<String, String>
     * ************************************
     */
    public Map<String, String> message(String message, int resultInt, String tabid) {
        mes.put("statusCode", resultInt == 0 ? "200" : "300");
        mes.put("message", message);
        mes.put("tabid", tabid);
        mes.put("closeCurrent", "true");
        return mes;
    }

    public Map<String, String> messageDialog(String message, int resultInt, String tabid) {
        mes.put("statusCode", resultInt == 0 ? "200" : "300");
        mes.put("message", message);
        mes.put("addedid", tabid);
        mes.put("closeCurrent", "true");
        return mes;
    }

    public Map<String, String> messageDialog(String message, boolean flag, String tabid) {
        mes.put("statusCode", flag == true ? "200" : "300");
        mes.put("message", message);
        mes.put("addedid", tabid);
        return mes;
    }

    /**
     * ************************************
     * AJAX输出HTML，返回null
     *
     * @param html
     * @return String
     * ************************************
     */
    public String ajaxHtml(String html) {
        return showMes(html, "text/html");
    }

    public String showMes(String content, String type) {
        try {
            HttpServletResponse response = this.response;
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存错误日志的格式
     *
     * @param request
     * @return
     */
    public static void printErrorMessage(String oprater, Exception e, HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();//获取除参数之外的地址信息
        String queryString = request.getQueryString();
        String serviceName = requestURL.substring(requestURL.lastIndexOf("/") + 1);//获取接口名称

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[操作人ip]" + IPUtils.getIpAddr(request));
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[请求报文]" + "操作描述:" + oprater + ":" + (("".equals(queryString) || null == queryString) ? requestURL : (requestURL + "?" + queryString)));
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[错误信息]" + Utils.getAllErrorMessage(e));
        stringBuffer.append("\r\n");

        ExceptionLog.error(serviceName, stringBuffer.toString());
    }


    /**
     * 保存请求日志
     *
     * @param request
     */
    public static void printRequestMessage(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();//获取除参数之外的地址信息
        String queryString = request.getQueryString();
        String serviceName = requestURL.substring(requestURL.lastIndexOf("/") + 1);//获取接口名称

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[操作人ip]" + IPUtils.getIpAddr(request));
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[请求报文]" + (("".equals(queryString) || null == queryString) ? requestURL : (requestURL + "?" + queryString)));
        stringBuffer.append("\r\n");

        ExceptionLog.resultInfo(serviceName, stringBuffer.toString(), 0);
    }

    /**
     * 保存响应日志
     *
     * @param request
     * @param data
     */
    public void printResponseMessage(HttpServletRequest request, String data) {
        String requestURL = request.getRequestURL().toString();//获取除参数之外的地址信息
        String queryString = request.getQueryString();
        String serviceName = requestURL.substring(requestURL.lastIndexOf("/") + 1);//获取接口名称

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[操作人ip]" + IPUtils.getIpAddr(request));
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[请求报文]" + (("".equals(queryString) || null == queryString) ? requestURL : (requestURL + "?" + queryString)));
        stringBuffer.append("\r\n");
        stringBuffer.append("\t");
        stringBuffer.append("[响应报文]" + data);
        stringBuffer.append("\r\n");

        ExceptionLog.resultInfo(serviceName, stringBuffer.toString(), 1);
    }


    /**
     * ************************************
     * setAttribute
     *
     * @param name
     * @param value ************************************
     */
    public void setAttribute(String name, Object value) {
        this.getRequest().setAttribute(name, value);
    }

    /**
     * ************************************
     * getParameter 根据参数名获取值
     *
     * @param argName
     * @param ************************************
     */
    public String getParameter(String argName) {
        return this.getRequest().getParameter(argName);
    }

    /**
     * ************************************
     * getRequest 获取request
     *
     * @param
     * @return HttpServletRequest
     * ************************************
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * ************************************
     * getResponse 获取response
     *
     * @param
     * @return HttpServletResponse
     * ************************************
     */
    public HttpServletResponse getResponse() {
        ServletWebRequest servletContainer = (ServletWebRequest) RequestContextHolder.getRequestAttributes();
        return servletContainer.getResponse();
    }

    /**
     * ************************************
     * getSession 获取session
     *
     * @param
     * @return HttpSession
     * ************************************
     */
    public HttpSession getSession() {
        return this.getRequest().getSession();
    }

    public Page getPage() {
        if (page == null) {
            page = new Page();
        }
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Map getMes() {
        return mes;
    }

    public void setMes(Map mes) {
        this.mes = mes;
    }

    public String getShowType() {
        return getPage().getParams().get("showType") + "";
    }

    public boolean isSearch() {
        //System.out.println(getPage().getParams());
        if ("1".equals(getShowType())) return true;
        //setDefaultDate();
        return false;
    }

    public boolean isSearchAndMinMaxDate() {
        //System.out.println(getPage().getParams());
        if ("1".equals(getShowType())) return true;
        //setDefaultDate();
        getPage().getParams().put("minDate", DateUtil.formatDate(new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000))));
        getPage().getParams().put("maxDate", Utils.formateDate(0));
        return false;
    }

    public AdminInfo getAdminInfo() {
        AdminInfo adminInfo = (AdminInfo) getSession().getAttribute("HFBACKSTAGEUSER");
//        String str = adminInfo.getAdmin_id().toString();
//        System.out.println(str);
        return adminInfo;
    }

    /**
     *  下载模板公用
     */
    public  void cnmonDownLoad(InputStream inputStream, String filename) throws Exception{
        BufferedInputStream inStrem = null;
        BufferedOutputStream outStream = null;
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            // Content-disposition 告诉浏览器以下载的形式打开
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename,"utf-8"));
            // application/ms-excel;charset=utf-8 告诉浏览器下载的文件是excel
            response.setContentType("application/ms-excel");
            //os = new FileInputStream(inputStream);
            inStrem = new BufferedInputStream(inputStream);
            outStream = new BufferedOutputStream(this.response.getOutputStream());

            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inStrem.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();
        } catch (IOException e) {
            throw  e;
        } finally {
            if(outStream!=null){
                outStream.close();
            }
            if(inputStream !=null){
                inputStream.close();
            }
            if(inStrem!=null){
                inStrem.close();
            }
        }
    }

}
