package com.yh.dao;

import com.yh.entity.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ************************************
 * 基础Dao
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public interface BaseDao<T,PK extends Serializable> {
	/**
	 * ************************************
	 * 添加
	 * @param 
	 * ************************************
	 */
	public int insert(T obj);
	public int insert(String statement, Object parameter);
	public int insert(String statement);
	
	/**
	 * ************************************
	 * 修改
	 * @param 
	 * ************************************
	 */
	public int update(T obj);
	public int update(String statement, Object parameter);
	public int update(String statement);
	
	/**
	 * ************************************
	 * 删除
	 * @param 
	 * ************************************
	 */
	public int delete(T obj);
	public int delete(String statement, Object parameter);
	public int delete(String statement);
	
	/**
	 * ************************************
	 * 查询集合
	 * @param 
	 * ************************************
	 */
	public void getPageList(Page p);
	public void getPageList(String statement, Page p);
	public List<Map<String,Object>> getList(Object parameter);
	public List<Map<String,Object>> getList(String statement, Object parameter);
	public List getObjectList(String statement);
	public List getObjectList(String statement, Object parameter);

	Map<String,Object> getOneMap(String mapper,Object para);
	/**
	 * ************************************
	 * 查询单个记录
	 * @param 
	 * ************************************
	 */
	public T getOne(Object parameter);
	public T getOne(String statement, Object parameter);
	public Object selectOne(String statement, Object parameter);
	public Integer getInt(String statement, Object parameter);
	public Long getLong(String statement, Object parameter);
	/**
	 * ************************************
	 * 统计数量
	 * @param 
	 * ************************************
	 */
	public Integer getCount(String statement);
	public Integer getCount(String statement, Object parameter);
	
}
