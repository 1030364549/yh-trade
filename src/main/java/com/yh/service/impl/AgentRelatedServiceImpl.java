package com.yh.service.impl;

import com.yh.dao.AgentRelatedDao;
import com.yh.entity.AdminInfo;
import com.yh.entity.AgentRelated;
import com.yh.entity.Page;
import com.yh.entity.PropertiesData;
import com.yh.service.AgentRelatedService;
import com.yh.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 〈成本管理ServiceImpl〉
 *
 * @author puyiliang
 * @date 2019/7/8  19:17
 * @since 1.0.0
 */
@Service
public class AgentRelatedServiceImpl extends BaseServiceImpl<AgentRelated, Integer> implements AgentRelatedService {
    @Resource
    private AgentRelatedDao agentRelatedDao;

    @Override
    public void getPageList(Page page) throws Exception {
        page.getParams().put("agent_count",6);
        agentRelatedDao.getPageList("AgentInfo.getPageList",page);
    }

    @Override
    public Map getOne(String id) throws Exception {
        return (Map) agentRelatedDao.getOne(id);
    }

    @Override
    public void saveAgentRelated(Map agentinfo, AdminInfo adminInfo) throws Exception {

        Date date = new Date();
        agentinfo.put("add_date",DateUtil.get4yMd(date));
        agentinfo.put("add_time",DateUtil.get4Hms(date));
        //添加人
        agentinfo.put("add_man",adminInfo.getAdmin_name());

        //检验费率是否存在（如果不存在保存费率）
        List<String> rateList = new ArrayList<>();
        rateList.add((String) agentinfo.get("debit_standard_rated"));
        rateList.add((String) agentinfo.get("debit_standard_rates"));
        rateList.add((String) agentinfo.get("credit_standard_rated"));
        rateList.add((String) agentinfo.get("credit_standard_rates"));
        rateList.add((String) agentinfo.get("debit_discount_rated"));
        rateList.add((String) agentinfo.get("debit_discount_rates"));
        rateList.add((String) agentinfo.get("credit_discount_rated"));
        rateList.add((String) agentinfo.get("credit_discount_rates"));
        rateList.add((String) agentinfo.get("debit_reduction_rated"));
        rateList.add((String) agentinfo.get("debit_reduction_rates"));
        rateList.add((String) agentinfo.get("credit_reduction_rated"));
        rateList.add((String) agentinfo.get("credit_reduction_rates"));
        this.saveAgentRelated(rateList, adminInfo);

         //标准类 借记
        AgentRelated debitStandard = new AgentRelated(agentinfo);
        debitStandard.setRated((String) agentinfo.get("debit_standard_rated"));
        debitStandard.setRetes((String) agentinfo.get("debit_standard_rates"));
        debitStandard.setRatetype("0");
        debitStandard.setCredit_type("1");
        //标准类 贷记
        AgentRelated creditStandard = new AgentRelated(agentinfo);
        creditStandard.setRated((String) agentinfo.get("credit_standard_rated"));
        creditStandard.setRetes((String) agentinfo.get("credit_standard_rates"));
        creditStandard.setRatetype("0");
        creditStandard.setCredit_type("2");
         //优惠类 借记
        AgentRelated debitDiscount = new AgentRelated(agentinfo);
        debitDiscount.setRated((String) agentinfo.get("debit_discount_rated"));
        debitDiscount.setRetes((String) agentinfo.get("debit_discount_rates"));
        debitDiscount.setRatetype("1");
        debitDiscount.setCredit_type("1");
         //优惠类 贷记
        AgentRelated creditDiscount = new AgentRelated(agentinfo);
        creditDiscount.setRated((String) agentinfo.get("credit_discount_rated"));
        creditDiscount.setRetes((String) agentinfo.get("credit_discount_rates"));
        creditDiscount.setRatetype("1");
        creditDiscount.setCredit_type("2");
         //减免类 借记
        AgentRelated debitReduction = new AgentRelated(agentinfo);
        debitReduction.setRated((String) agentinfo.get("debit_reduction_rated"));
        debitReduction.setRetes((String) agentinfo.get("debit_reduction_rates"));
        debitReduction.setRatetype("2");
        debitReduction.setCredit_type("1");
         //减免类 贷记
        AgentRelated creditReduction = new AgentRelated(agentinfo);
        creditReduction.setRated((String) agentinfo.get("credit_reduction_rated"));
        creditReduction.setRetes((String) agentinfo.get("credit_reduction_rates"));
        creditReduction.setRatetype("2");
        creditReduction.setCredit_type("2");


        List<AgentRelated> addList = new ArrayList<>();
        addList.add(debitStandard);
        addList.add(creditStandard);
        addList.add(debitDiscount);
        addList.add(creditDiscount);
        addList.add(debitReduction);
        addList.add(creditReduction);
        //保存成本
        agentRelatedDao.insert("saveAgentRelated",addList);
    }

    @Override
    public void updateAgentRelated(Map agentRelated, AdminInfo adminInfo) throws Exception {
        //非顶级机构限制
        Integer obj_no = adminInfo.getObj_no();
        if(!PropertiesData.AGENT_NUM.equals(String.valueOf(obj_no))){
            agentRelated.put("obj_no",obj_no);
        }
        //获取旧成本列表
        List relatedList = agentRelatedDao.getObjectList("getRelatedByInfo",agentRelated);
        Date date = new Date();
        //需要变更的成本列表
        List needUpdate = new ArrayList();
        List rateList = new ArrayList();
        for (Object o : relatedList) {
            Map p = (Map)o;
            String rateType = (String)agentRelated.get("RATETYPE");
            String creditType = (String)agentRelated.get("CREDIT_TYPE");
            String idKey = rateType+"_"+creditType;
            switch (idKey){
                case "0_1":idKey = "debit_standard";break;
                case "0_2":idKey = "credit_standard";break;
                case "1_1":idKey = "debit_discount";break;
                case "1_2":idKey = "credit_discount";break;
                case "2_1":idKey = "debit_reduction";break;
                case "2_2":idKey = "credit_reduction";break;
            }
            String ratedKey =  idKey+"_rated";
            String ratesKey =  idKey+"_rates";
            String old_rated = (String)p.get("RATED");
            String old_rates = (String)p.get("RATES");
            String rated = (String)agentRelated.get(ratedKey);
            String rates = (String)agentRelated.get(ratesKey);
            //如果有变动
            if (!old_rated.equals(rated) || !old_rates.equals(rates)) {
                HashMap<Object, Object> map = new HashMap<>();
                map.put("id", p.get("ID"));
                map.put("rated", rated);
                map.put("rates", rates);
                rateList.add(rated);
                rateList.add(rates);
                String modifynote = p.get("MODIFYNOTE") == null ? "" : (String) p.get("MODIFYNOTE");
                //新留存信息
                String newModifyNote = modifynote + adminInfo.getAdmin_name() + "-" + agentRelated.get("agent_num")
                        + "-" + agentRelated.get("agent_name") + "-" + p.get("RATED") + "-" + p.get("RATES")
                        + "-" + DateUtil.get4yMd(date) + " " + DateUtil.get4Hms(date) + ";";
                map.put("modifynote", newModifyNote);
                needUpdate.add(map);
            }
        }
        if (needUpdate.size() > 0){
            saveAgentRelated(rateList, adminInfo);
            agentRelatedDao.update("updateAgentRelated", needUpdate);
        }
    }


    public void saveAgentRelated(List<String> rateAll, AdminInfo adminInfo)  throws Exception{
        //需要处理的费率
        List<HashMap> needSaveRate = new ArrayList<>();
        List<HashMap> needSaveRateAlorithm = new ArrayList<>();
        Date date = new Date();
        if (rateAll != null && rateAll.size() >0){
            //查询已经存在的费率
            Set<String> set = new HashSet<>(rateAll);
            List<String> list = new ArrayList<>(set);
            List<String> rateList = agentRelatedDao.getObjectList("getRateList", list);
            for (String rateMark : list) {
                if (!rateList.contains(rateMark)){
                    HashMap<String, String> rateMap = new HashMap<>();
                    if (rateMark.startsWith("CAP")){
                        String splitRateMark = rateMark.substring(3);
                        String[] rs = splitRateMark.split("R");
                        //计算封顶
                        BigDecimal top = new BigDecimal(rs[0]).multiply(new BigDecimal("0.01"));
                        //计算费率
                        BigDecimal rate = new BigDecimal(rs[1]).multiply(new BigDecimal("0.00001"));
                        rateMap.put("ar_mark", rateMark);
                        rateMap.put("ar_name", top.stripTrailingZeros().toPlainString()+"元封顶,"+rate.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString()+"%费率");
                        rateMap.put("ar_content","(0-"+top.divide(rate,0,RoundingMode.HALF_DOWN).stripTrailingZeros().toPlainString()+")"+rate.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString()+"%费率,"+top.stripTrailingZeros().toPlainString()+"元封顶");
                        rateMap.put("add_time",DateUtil.get4yMd(date) +" "+DateUtil.get4Hms(date));
                        rateMap.put("add_man",adminInfo.getObj_no().toString());
                        rateMap.put("armark_fl",rate.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString()+"%");
                        rateMap.put("armark_fd",top.stripTrailingZeros().toPlainString());
                        needSaveRate.add(rateMap);
                        //费率规则算法
                        HashMap<String, String> rateTopMap = new HashMap<>();
                        HashMap<String, String> rateRMap = new HashMap<>();
                        rateTopMap.put("ar_mark", rateMark);
                        rateRMap.put("ar_mark", rateMark);
                        rateTopMap.put("onset_money", top.stripTrailingZeros().toPlainString()+".00");
                        rateRMap.put("onset_money", "0.00");
                        rateTopMap.put("end_money", "999999999.00");
                        rateRMap.put("end_money", top.stripTrailingZeros().toPlainString()+".00");
                        rateTopMap.put("ar_content", top.stripTrailingZeros().toPlainString());
                        rateRMap.put("ar_content", "P*"+rate.stripTrailingZeros().toPlainString());
                        rateTopMap.put("ar_type", "1");
                        rateRMap.put("ar_type", "0");
                        needSaveRateAlorithm.add(rateTopMap);
                        needSaveRateAlorithm.add(rateRMap);
                    }else if (rateMark.startsWith("R")){
                        String splitRateMark = rateMark.substring(1);
                        //计算费率
                        BigDecimal rate = new BigDecimal(splitRateMark).multiply(new BigDecimal("0.00001"));
                        rateMap.put("ar_mark", rateMark);
                        rateMap.put("ar_name", rate.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()+"%费率");
                        rateMap.put("ar_content",rate.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()+"%费率");
                        rateMap.put("add_time",DateUtil.get4yMd(date) +" "+DateUtil.get4Hms(date));
                        rateMap.put("add_man",adminInfo.getObj_no().toString());
                        rateMap.put("armark_fl",rate.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()+"%");
                        needSaveRate.add(rateMap);
                        //费率规则算法
                        HashMap<String, String> rateRMap = new HashMap<>();
                        rateRMap.put("ar_mark", rateMark);
                        rateRMap.put("onset_money", "0.00");
                        rateRMap.put("end_money", "999999999.00");
                        rateRMap.put("ar_content", "P*"+rate.stripTrailingZeros().toPlainString());
                        rateRMap.put("ar_type", "0");
                        needSaveRateAlorithm.add(rateRMap);
                    }
                }
            }
        }
        //存在需要处理的费率
        if(needSaveRate.size() > 0){
            agentRelatedDao.insert("saveRateInfo",needSaveRate);
            agentRelatedDao.insert("saveRateAlgorithm",needSaveRateAlorithm);
        }
    }
}
