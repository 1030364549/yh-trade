package com.yh.dao.impl;

import com.yh.dao.IncrementLogDao;
import com.yh.entity.IncrementLog;
import org.springframework.stereotype.Repository;

@Repository
public class IncrementLogDaoImpl extends BaseDaoImpl<IncrementLog, Integer> implements IncrementLogDao{
}
