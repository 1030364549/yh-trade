package com.yh.entity;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * ************************************
 * Page 分页类
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
@Scope("prototype")
public class Page {
	//排序方式
	public enum OrderDirection{
		asc,desc
	}
	private String orderField;//排序字段
	private OrderDirection orderDirection=OrderDirection.desc;//排序方式
	
	private int pageNo=1;//页码，默认第 1 页
	private int pageSize=20;//每页显示记录数
	private int totalRecord=0;//总记录数
	private int totalPage;//总页数
	private List results;//对应的当前页记录
	private HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	private Map<String,Object> params=new HashMap<String,Object>();//其他的参数我们把它分装成一个Map对象
	
	public Page(){
		Enumeration enu=request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName=(String) enu.nextElement();
			//参数，排除分页等参数
			String notInclude="_,pageNum,orderField,orderDirection,numPerPage,totalCount";
			if (notInclude.indexOf(paraName)==-1) {
				params.put(paraName.replace("[", "").replace("]", ""), request.getParameter(paraName).trim());
			}
		}
		
		if (request.getParameter("pageNum")!=null) {
			this.setOrderField(request.getParameter("orderField"));
			this.setOrderDirection(request.getParameter("orderDirection").equals("desc")?OrderDirection.desc:OrderDirection.asc);
			this.setPageNo(Integer.valueOf(params.get("pageCurrent").toString()));
			System.out.println("--"+params.get("pageSize").toString());
			this.setPageSize(Integer.parseInt(params.get("pageSize").toString()));
			this.setTotalRecord(Integer.parseInt(params.get("totalRecord").toString()));
		}
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public OrderDirection getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(OrderDirection orderDirection) {
		this.orderDirection = orderDirection;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		//设置总记录数时计算出总页数
		int totalPage=totalRecord%pageSize==0?totalRecord/pageSize:totalRecord/pageSize+1;
		if (pageNo>totalPage) {
			this.pageNo=1;
			this.getParams().put("pageCurrent", "1");
		}
		this.setTotalPage(totalPage);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().setAttribute("params", params);
		this.params = params;
	}
	
}
