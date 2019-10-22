package com.yh.dao.impl;

import org.springframework.stereotype.Repository;

import com.yh.dao.AgentInfoDao;
import com.yh.entity.AgentInfo;
/**
 * ************************************
 * dao实现层：代理商表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@Repository
public class AgentInfoDaoImpl extends BaseDaoImpl<AgentInfo, Integer> implements
		AgentInfoDao {

}
