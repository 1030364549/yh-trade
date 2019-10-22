package com.yh.service.impl;

import com.yh.dao.AreaDao;
import com.yh.entity.Area;
import com.yh.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class AreaServiceImpl extends BaseServiceImpl<Area, Integer>
		implements AreaService {
	@Resource
	private AreaDao areaDao;
	/*******************************引用Dao**********************************/
	@Resource
	public void setAreaDao(AreaDao areaDao){
		super.setBaseDao(areaDao);
	}

	
	@Override
	public List<Map<String, Object>> findArea(String index) {
		return areaDao.getList("findArea",index);
	}

	@Override
	public List<Map<String, Object>> findAreaforagent(Map map) {
		return areaDao.getList("findAreaforagent",map);
	}

}
