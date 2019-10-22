package com.yh.service.impl;

import com.yh.dao.BankExpenditureDao;
import com.yh.entity.BankExpenditure;
import com.yh.entity.Page;
import com.yh.service.BankExpenditureService;
import com.yh.util.ExportExcel;
import com.yh.util.RfExcelUtil;
import com.yh.util.Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankExpenditureServiceImpl extends BaseServiceImpl<BankExpenditure, Integer> implements BankExpenditureService {

    @Resource
    private BankExpenditureDao bankExpenditureDao;

    public static Map<String,String> TOP=new HashMap<String,String>(){
        {
            put("AGENT_NUM","机构编号");
            put("AGENT_NAME","机构名称");
            put("MERNO","商户编号");
            put("MER_NAME","商户名称");
            put("SERIAL","交易流水号");
            put("MSGTYPE","交易类型");
            put("LOCALDATE","交易日期");
            put("LOCALTIME","交易时间");
            put("PAN","交易卡号");
            put("AMOUNT","交易金额");
            put("RC","交易结果");
            put("STATUS","交易状态");
            put("STAN","终端流水号");
            put("CARDTYPE","卡类型");
            put("SETT_TYPE","结算类型");
            put("AU_STATE","清算状态");
            put("BI_STATE","对账状态");
            put("PHONEPAY","交易类型");
        }
    };


    public static Map<String,String> STATISTICS_TOP=new HashMap<String,String>(){
        {
            put("LOCALDATE","交易日期");
            put("AGENT_NUM","机构编号");
            put("AGENT_NAME","机构名称");
            put("TOTAL","交易笔数");
            put("AMOUNT","交易金额");
            put("STANDARD_TOTAL","标准交易笔数");
            put("STANDARD_AMOUNT","标准交易金额");
            put("REDUCTION_TOTAL","减免交易笔数");
            put("REDUCTION_AMOUNT","减免交易金额");
            put("DISCOUNT_TOTAL","优惠交易笔数");
            put("DISCOUNT_AMOUNT","优惠交易金额");
            put("SPECIAL_TOTAL","特殊计费交易笔数");
            put("SPECIAL_AMOUNT","特殊计费交易金额");
            put("DEBIT_TOTAL","借记交易笔数");
            put("DEBIT_AMOUNT","借记交易金额");
            put("CREDIT_TOTAL","贷记交易笔数");
            put("CREDIT_AMOUNT","贷记交易金额");
        }
    };

    @Override
    public void getExportExcelList(Map param, HttpServletResponse response) throws Exception {
        List list = bankExpenditureDao.getObjectList("getExportExcelList", param);
        //导出当前页 分页
        subList(param, list);
        if (list.size() > 0){
            //转换值
            list = processingDataList(list);
        }
        String [] names=new String[]{"AGENT_NUM","AGENT_NAME","MERNO","MER_NAME","SERIAL","MSGTYPE","LOCALDATE",
                "LOCALTIME","PAN","AMOUNT","RC","STATUS","STAN","CARDTYPE","SETT_TYPE","AU_STATE","BI_STATE","PHONEPAY"};
        //导出
        String fileName = Utils.formateDate(0)+"交易信息.xlsx";
        ExportExcel.exportExcel(list, names,fileName,response,TOP);
    }

    @Override
    public void getStatisticsExportExcelList(Map param, HttpServletResponse response) throws Exception {
        List list = bankExpenditureDao.getObjectList("getStatisticsExportExcelList", param);
        //导出当前页 分页
        subList(param, list);
        //导出
        String [] names=new String[]{"LOCALDATE","AGENT_NUM","AGENT_NAME","TOTAL","AMOUNT","STANDARD_TOTAL","STANDARD_AMOUNT",
                "REDUCTION_TOTAL","REDUCTION_AMOUNT","DISCOUNT_TOTAL","DISCOUNT_AMOUNT","SPECIAL_TOTAL","SPECIAL_AMOUNT","DEBIT_TOTAL","DEBIT_AMOUNT","CREDIT_TOTAL","CREDIT_AMOUNT"};
        String fileName = Utils.formateDate(0)+"交易统计.xlsx";
        //导出表
        ExportExcel.exportExcel(list, names,fileName,response,STATISTICS_TOP);
    }

    @Override
    public void getPageList(String statement,Page page) {
        bankExpenditureDao.getPageList(statement, page);
    }

    public List subList(Map params, List list){
        if (!Utils.IsNull(params.get("pageNum"))){
            int size = list.size();
            int pageNo = Integer.parseInt((String) params.get("pageNum"));
            int pageSize = Integer.parseInt((String) params.get("numPerPage"));
            int pageCount=size/pageSize;
            int fromIndex = pageSize * (pageNo - 1);
            int toIndex = fromIndex + pageSize;
            if (toIndex >= size) {
                toIndex = size;
            }
            if(pageNo>pageCount+1){
                fromIndex=0;
                toIndex=0;
            }
            list = list.subList(fromIndex, toIndex);
        }
        return list;
    }

    public List processingDataList(List list){
        for (Object o : list) {
            Map map = (Map)o;
            String status = (String) map.get("STATUS");
            switch (status){
                case "-1":map.put("STATUS","撤销");break;
                case "-2":map.put("STATUS","冲正");break;
                case "0":map.put("STATUS","成功");break;
                case "1":map.put("STATUS","补录");break;
                case "2":map.put("STATUS","失败");break;
            }
            String cardtype = (String) map.get("CARDTYPE");
            switch (cardtype){
                case "0":map.put("CARDTYPE","未知");break;
                case "1":map.put("CARDTYPE","借记卡");break;
                case "2":map.put("CARDTYPE","贷记卡");break;
                case "3":map.put("CARDTYPE","预付费卡");break;
            }
            String sett_type = (String) map.get("SETT_TYPE");
            switch (sett_type){
                case "0":map.put("SETT_TYPE","S0");break;
                case "1":map.put("SETT_TYPE","D1");break;
            }
            String au_state = (String) map.get("AU_STATE");
            switch (au_state){
                case "0":map.put("AU_STATE","未清");break;
                case "1":map.put("AU_STATE","已清");break;
            }
            String bi_state = (String) map.get("BI_STATE");
            switch (bi_state){
                case "0":map.put("BI_STATE","未对账");break;
                case "1":map.put("BI_STATE","已对账");break;
            }
            String phonepay = (String) map.get("PHONEPAY");
            switch (phonepay){
                case "0":map.put("PHONEPAY","普通交易");break;
                case "1":map.put("PHONEPAY","手机Pay");break;
            }
        }
        return list;
    }

}
