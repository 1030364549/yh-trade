package com.yh.dao.impl;


import com.yh.dao.IncrementAccountsDao;
import com.yh.entity.IncrementAccounts;
import org.springframework.stereotype.Repository;

@Repository
public class IncrementAccountsDaoImpl extends BaseDaoImpl<IncrementAccounts, Integer> implements IncrementAccountsDao {
}
