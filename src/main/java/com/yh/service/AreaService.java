package com.yh.service;

import com.yh.entity.Area;

import java.util.List;
import java.util.Map;

/**
 * ************************************
 * XXXService
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public interface AreaService extends BaseService<Area, Integer> {

	List<Map<String, Object>> findArea(String index);

	List<Map<String, Object>> findAreaforagent(Map map);

}
