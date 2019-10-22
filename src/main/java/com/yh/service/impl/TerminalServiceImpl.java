package com.yh.service.impl;

import com.yh.dao.TerminalDao;
import com.yh.entity.Page;
import com.yh.entity.Terminal;
import com.yh.service.TerminalService;
import com.yh.util.ExcelUtil;
import com.yh.util.ExportExcel;
import com.yh.util.RfExcelUtil;
import com.yh.util.Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class TerminalServiceImpl extends BaseServiceImpl<Terminal, Integer>
		implements TerminalService {
	@Resource
	private TerminalDao terminalDao;

	/**
	 *
	 *********************************************************.<br>
	 * [方法] getTerminalList <br>
	 * [描述] 终端信息查询 <br>
	 * [参数] [Page](对参数的描述) <br>
	 * [返回]  <br>
	 * [日期] 2019/7/9
	 * [时间] 10:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@Override
	public void getPageList(Page page)  throws Exception{
		terminalDao.getPageList("getTerminalList", page);
	}

	public static Map<String,String> TOP=new HashMap<String,String>(){
		{
			put("AGENT_NUM","机构编号");
			put("AGENT_NAME","机构名称");
			put("MERNO","商户编号");
			put("MER_NAME","商户名称");
			put("POSNO","终端编号");
			put("TMK","终端密钥");
			put("CREATEDATE","添加日期");
		}
	};

	@Override
	public void getExportExcelList(Map param, HttpServletResponse response) throws Exception{
		List list = terminalDao.getObjectList("getExportExcelList", param);
		if (!Utils.IsNull(param.get("pageNum"))){
			int size = list.size();
			int pageSize = Integer.parseInt((String) param.get("numPerPage"));
			int pageNo = Integer.parseInt((String) param.get("pageNum"));
			int pageCount=size/pageSize;
			int fromIndex = pageSize * (pageNo - 1);
			int toIndex = fromIndex + pageSize;
			if (toIndex >= size) {
				toIndex = size;
			}
			if(pageNo>pageCount+1){
				fromIndex=0;
				toIndex=0;
			}
			list = list.subList(fromIndex, toIndex);
		}
		//数据字段
		String [] names=new String[]{"AGENT_NUM","AGENT_NAME","MERNO","MER_NAME","POSNO","CREATEDATE"};
		String fileName = Utils.formateDate(0)+"终端信息.xlsx";
		//导出表
		ExportExcel.exportExcel(list, names,fileName,response,TOP);
	}

}
