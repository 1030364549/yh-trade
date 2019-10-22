package com.yh.service;
import com.yh.entity.AdminInfo;
import com.yh.entity.AgentInfo;
import com.yh.entity.IncrementLog;
import com.yh.entity.Page;

import java.util.Map;
/**
 * @Author: Liyh
 * @Date: 2019/10/14 14:00
 * @Description:   增值账户
 * @Version: 1.0
 */
@SuppressWarnings("all")
public interface IncrementAccountsService {
    void getPageList(Page page)throws Exception;

    Map getIncrementAccountsOne(Map incrementAccounts) throws Exception;

    Map saveBalance(AdminInfo adminInfo, Map incrementLog)throws Exception;

    void saveAccounts(Map incrementAccounts)throws Exception;

    void modifyAccounts(Map incrementAccounts)throws Exception;
}
