package com.yh.service.impl;

import com.yh.dao.AccountsDao;
import com.yh.dao.AgentInfoDao;
import com.yh.dao.AgentRelatedDao;
import com.yh.entity.AdminInfo;
import com.yh.entity.AgentInfo;
import com.yh.entity.AgentRelated;
import com.yh.entity.Page;
import com.yh.service.AgentInfoService;
import com.yh.util.DateUtil;
import com.yh.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

@SuppressWarnings("all")
@Service
public class AgentInfoServiceImpl extends BaseServiceImpl<AgentInfo, Integer> implements AgentInfoService {
	@Resource
	private AgentInfoDao agentinfodao;
	@Resource
	private AccountsDao accountsdao;
	@Resource
	private AgentRelatedDao agentRelatedDao;

	@Override
	public void getPageList(Page page) {
		agentinfodao.getPageList(page);
	}

	@Override
	public void saveAgentInfo(@RequestParam Map agentinfo)  throws Exception{
		//省市
		String area_name=agentinfo.get("province")+","+agentinfo.get("city");
		agentinfo.put("area_name",area_name);
		//账户开户地
		String area_account_name = agentinfo.get("accounts_province")+","+agentinfo.get("accounts_city");
		agentinfo.put("bank_address",area_account_name);

		//身份证银行卡手机号脱敏
		Map encryption = Utils.encryption(agentinfo.get("identity_num").toString(), agentinfo.get("screen_num").toString(), agentinfo.get("link_phone").toString());
		//agentinfo.put("identity_num",encryption.get(""));
		agentinfo.put("show_identitynum",encryption.get("idcard"));
		agentinfo.put("screen_idnum",encryption.get("idcardjm"));
		agentinfo.put("show_screenidnum",encryption.get("idcard"));
		agentinfo.put("resphone",encryption.get("phonejm"));
		agentinfo.put("show_resphone",encryption.get("phone"));
		agentinfo.put("screen_num",encryption.get("bankcardjm"));
		agentinfo.put("show_screennum",encryption.get("bankcard"));

		Date date = new Date();

		//添加日期
		agentinfo.put("localdate", DateUtil.get4yMd(date));
		//添加时间
		agentinfo.put("localtime",DateUtil.get4Hms(date));
		agentinfo.put("createdate", date);
		Integer agentNum = agentinfodao.getInt("getAgentNum", "");
		agentinfo.put("merno",agentNum);
		agentinfodao.insert("save", agentinfo);
		accountsdao.insert("saveAccount", agentinfo);
	}

	@Override
	public Map getAgentOne(Map agentInfo)  throws Exception{
		Map agentinfo = (Map) agentinfodao.selectOne("getOne", agentInfo);
		//账户开户地 地址查询
		String bank_address = (String) agentinfo.get("BANK_ADDRESS");
		if (!Utils.IsNull(bank_address)) {
			String[] split = bank_address.split(",");
			agentinfo.put("ACCOUNTS_PROVINCE", split[0]);
			agentinfo.put("ACCOUNTS_CITY", split[1]);
		}
		return agentinfo;
	}

	@Override
	public void updateAgentInfo(Map agentinfo) throws Exception {

		//省市
		String area_name=agentinfo.get("province")+","+agentinfo.get("city");
		agentinfo.put("area_name",area_name);
		//账户开户地
		String area_account_name = agentinfo.get("accounts_province")+","+agentinfo.get("accounts_city");
		agentinfo.put("bank_address",area_account_name);
		//存在主键 更新机构信息
		agentinfodao.update("updateAgent", agentinfo);
		accountsdao.update("updateAccount", agentinfo);
	}

	@Override
	public void updateAgentType(Map agentInfo) throws Exception{
		agentinfodao.update("updateAgentType",agentInfo);
	}

	@Override
	public Map agentRelatedAdd(Map agentRelateds) throws Exception{
		Map agentinfo = (Map) agentinfodao.selectOne("getAgentInfo", agentRelateds);
		List<Map<String, Object>> list = agentRelatedDao.getList("getRelatedByInfo", agentRelateds);
		//成本已经存在
		if (list != null && list.size() > 0){
			for (Map agentRelated: list) {
				String rateType = (String)agentRelated.get("RATETYPE");
				String creditType = (String)agentRelated.get("CREDIT_TYPE");
				String idKey = rateType+"_"+creditType;
				switch (idKey){
					case "0_1":idKey = "DEBIT_STANDARD";break;
					case "0_2":idKey = "CREDIT_STANDARD";break;
					case "1_1":idKey = "DEBIT_DISCOUNT";break;
					case "1_2":idKey = "CREDIT_DISCOUNT";break;
					case "2_1":idKey = "DEBIT_REDUCTION";break;
					case "2_2":idKey = "CREDIT_REDUCTION";break;
				}
				String ratedKey =  idKey+"_RATED";
				String ratesKey =  idKey+"_RATES";
				agentinfo.put(idKey,agentRelated.get("ID"));
				agentinfo.put(ratedKey,agentRelated.get("RATED"));
				agentinfo.put(ratesKey,agentRelated.get("RATES"));
			}
		}
		return agentinfo;
	}
}
