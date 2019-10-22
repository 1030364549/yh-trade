package com.yh.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.yh.dao.BaseDao;
import com.yh.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * ************************************
 * 业务处理层接口实现
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
@Service
@Transactional
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	private BaseDao<T, PK> baseDao;

	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}
}
