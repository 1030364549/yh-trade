package com.yh.service;

import com.yh.entity.Accounts;

import java.util.Map;

/**
 * ************************************
 * XXXService
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public interface AccountsService extends BaseService<Accounts, Integer> {


    Map getOne(String agent_num, String s);
}
