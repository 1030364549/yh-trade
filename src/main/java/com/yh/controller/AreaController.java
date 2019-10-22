package com.yh.controller;

import com.yh.service.AreaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("Area")
public class AreaController extends BaseController {
	@Resource
	private AreaService areaService;
	
	/**
	 *********************************************************.<br>
	 * [方法] findArea <br>
	 * [描述] 查询地区信息 <br>
	 * [参数] 地区码 <br>
	 * [返回] Map<String,Object> <br>
	 * [时间] 2018年10月30日 下午3:49:43 <br>
	 *********************************************************.<br>
	 */
	@RequestMapping("findArea")
	public @ResponseBody List<String[]> findArea(String index){
		System.out.println(index);
		List<String[]> dataList=new ArrayList<>();
		dataList.add(new String[]{"请选择","请选择"});
		List<Map<String,Object>> list=areaService.findArea(index);
		for(int i=0;i<list.size();i++){
			Map<String,Object> data=list.get(i);
			String[] shu=new String[2];
			shu[0]=data.get("AREA_ID").toString();
			shu[1]=data.get("AREA_NAME").toString();
			dataList.add(shu);
		}

		return dataList;
	}
}
