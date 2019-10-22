package com.yh.cache;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ************************************
 * GetCacheMethod Freemarker自定义方法 实现response.encodeURL(url)功能
 * @param 
 * @param 
 * ************************************
 */
@SuppressWarnings("all")
public class GetCacheMethod implements TemplateMethodModelEx {
	
	private HttpServletResponse response;
	
	/**
	 * ************************************
	 * 带参构造函数
	 * @param response
	 * @param 
	 * ************************************
	 */
	public GetCacheMethod(HttpServletResponse response){
		this.response=response;
	}

	/**
	 * ************************************
	 * 执行方法
	 * @param argList 方法参数列表 
	 * @return Object 返回值
	 * ************************************
	 */
	public Object exec(List argList) throws TemplateModelException {
		DataCache dc=new DataCache();
		int argsize=argList.size();
		switch (argsize) {
		case 1:
			return dc.getCache(argList.get(0).toString());
		case 2:
			return dc.getCache((String)argList.get(0), (String)argList.get(1));
		case 3:
			return dc.getCache((String)argList.get(0), (String)argList.get(1), (String)argList.get(2));
		case 4:
			return dc.getCache((String)argList.get(0), (String)argList.get(1), (String)argList.get(2), (String)argList.get(3));
		}
		throw new TemplateModelException("Wrong arguments!");
	}

}
