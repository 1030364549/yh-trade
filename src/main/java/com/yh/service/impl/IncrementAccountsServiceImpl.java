package com.yh.service.impl;

import com.yh.dao.IncrementAccountsDao;
import com.yh.dao.IncrementLogDao;
import com.yh.entity.AdminInfo;
import com.yh.entity.IncrementAccounts;
import com.yh.entity.Page;
import com.yh.entity.PropertiesData;
import com.yh.service.IncrementAccountsService;
import com.yh.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * @Author: Liyh
 * @Date: 2019/10/14 15:59
 * @Description:   增值账户
 * @Version: 1.0
 */
@SuppressWarnings("all")
@Service
public class IncrementAccountsServiceImpl extends BaseServiceImpl<IncrementAccounts, Integer> implements IncrementAccountsService {

    @Resource
    private IncrementAccountsDao incrementAccountsDao;
    @Resource
    private IncrementLogDao incrementLogDao;
    @Override
    public void getPageList(Page page) throws Exception {
        incrementAccountsDao.getPageList(page);
    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 18:50
     * @Description:   查询一个增值账户
     * @Version: 1.0
     */
    @Override
    public Map getIncrementAccountsOne(Map incrementAccounts) throws Exception {
        return (Map) incrementAccountsDao.selectOne("getIncrementAccountsOne",incrementAccounts);

    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 19:10
     * @Description:   添加余额
     * @Version: 1.0
     */
    @Override
    public Map saveBalance(AdminInfo adminInfo, Map incrementLog) {
        Map<String,String> reCode = new HashMap<>(2);

        if(!PropertiesData.AGENT_NUM.equals(String.valueOf(adminInfo.getObj_no()))){
            incrementLog.put("obj_no",adminInfo.getObj_no());
        }
        int updateBalance = incrementAccountsDao.update("updateBalance", incrementLog);
        if(updateBalance<1){
            reCode.put("reCode","1");
            reCode.put("msg","充值失败");
            return reCode;
        }
        incrementLog.put("type",1);
        incrementLog.put("create_name",adminInfo.getAdmin_realname());//创建人id
        incrementLog.put("agent_name",incrementLog.get("agent_num"));
        incrementLog.put("account", incrementLog.get("amount")); //充值金额
        incrementLog.put("create_date",DateUtil.get4yMd(DateUtil.getDate()));
        incrementLog.put("create_time",DateUtil.get4Hms(DateUtil.getDate()));
        incrementLog.put("create_id",adminInfo.getAdmin_id());
        int addBalanceLog = incrementLogDao.insert("addBalanceLog", incrementLog);
        if (addBalanceLog<1){
            reCode.put("reCode","1");
            reCode.put("msg","充值记录失败");
            return reCode;
        }
        reCode.put("reCode","0");
        reCode.put("msg","成功");
        return reCode;

    }
    /**
     * @Author: Liyh
     * @Date: 2019/10/14 21:59
     * @Description:   保存增值账户
     * @Version: 1.0
     */
    @Override
    public void saveAccounts(Map incrementAccounts) {
            incrementAccounts.put("amount",0.0);
            incrementAccounts.put("insurance_amount",0.0);
            incrementAccounts.put("message_amount",0.0);
        incrementAccountsDao.insert("saveAccounts",incrementAccounts);
    }

    /**
     * @Author: Liyh
     * @Date: 2019/10/16 14:17
     * @Description:   修改账户为通用
     * @Version: 1.0
     */
    @Override
    public void modifyAccounts(Map incrementAccounts) throws Exception {
        incrementAccounts.put("is_currency",1);
        incrementAccountsDao.update("modifyAccounts",incrementAccounts);
    }
}
