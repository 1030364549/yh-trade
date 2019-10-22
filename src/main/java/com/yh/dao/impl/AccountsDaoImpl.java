package com.yh.dao.impl;

import org.springframework.stereotype.Repository;
import com.yh.dao.AccountsDao;
import com.yh.entity.Accounts;
/**
 * ************************************
 * dao实现层：XXX表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@Repository
public class AccountsDaoImpl extends BaseDaoImpl<Accounts, Integer> implements AccountsDao {

}
