package com.yh.service.impl;

import com.yh.dao.AccountsDao;
import com.yh.entity.Accounts;
import com.yh.service.AccountsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class AccountsServiceImpl extends BaseServiceImpl<Accounts, Integer>
		implements AccountsService {
	@Resource
	private AccountsDao accountsDao;

	@Override
	public Map getOne(String seq_id,String type) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("seq_id", seq_id);
		map.put("type", type);
		Map getOne = (Map) accountsDao.selectOne("getOne", map);
		return getOne;
	}
}
