package com.yh.service.impl;

import com.yh.dao.IncrementLogDao;
import com.yh.entity.IncrementLog;
import com.yh.entity.Page;
import com.yh.service.IncrementLogService;
import com.yh.util.ExportExcel;
import com.yh.util.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Liyh
 * @Date: 2019/10/15 9:10
 * @Description:
 * @Version: 1.0
 */
@SuppressWarnings("all")
@Service
public class IncrementLogServiceImpl extends BaseServiceImpl<IncrementLog, Integer> implements IncrementLogService {
    @Resource
    private IncrementLogDao incrementLogDao;
    @Override
    public void getPageList(Page page) throws Exception {
        incrementLogDao.getPageList(page);
    }
    @Override
    public void getPageList(String statment,Page page) throws Exception {
        incrementLogDao.getPageList(statment,page);
    }

    /**
     * 获得excelList
     * @param param
     * @param response
     * @throws Exception
     */
    @Override
    public void getExportExcelList(Map param, HttpServletResponse response) throws Exception {
        List list = incrementLogDao.getObjectList("getExportExcelList", param);
        if (list.size() > 0){
            //转换值
            list = processingDataList(list);
        }
        //数据字段
        String [] names=new String[]{"AGENT_NUM","AGENT_NAME",
        "AMOUNT","TYPE","INSURANCE_TYPE","CREATE_DATE","CREATE_TIME",
                "CREATE_NAME","NOTE"};
        String fileName = Utils.formateDate(0)+"充值记录.xlsx";
        //导出表：数据、数据字段、文件名、response、Map(文件第一行)
        ExportExcel.exportExcel(list, names,fileName,response,TOP);
    }

    /**
     * 获得增值服务统计的excelList
     * @param param
     * @param response
     * @throws Exception
     */
    @Override
    public void getExportExcelListStatistics(Map param, HttpServletResponse response) throws Exception {
        List list = incrementLogDao.getObjectList("getExportExcelListStatistics",param);
        if (list.size() > 0){
            //转换值
            list = processingDataList(list);
        }
        //数据字段
        String [] names=new String[]{"AGENT_NUM","AGENT_NAME",
                "SUMAMOUNT","TYPE","INSURANCE_TYPE","CREATE_DATE","COUNTNUM"};
        String fileName = Utils.formateDate(0)+"充值记录.xlsx";
        //导出表：数据、数据字段、文件名、response、Map(文件第一行)
        ExportExcel.exportExcel(list,names,fileName,response,TOP_STATISTICS);
    }

    /**
     * 加工从数据库取出的list
     * @param list
     * @return
     */
    public List processingDataList(List list){
        for (Object o : list) {
            Map map = (Map)o;
            String type = String.valueOf(map.get("TYPE"));
            switch (type){
                case "1":map.put("TYPE","充值");break;
                case "2":map.put("TYPE","消费");break;
                default:map.put("TYPE","无");break;
            }
            String insuranceType= String.valueOf(map.get("INSURANCE_TYPE"));
            switch (insuranceType){
                case "1":map.put("INSURANCE_TYPE","保险");break;
                case "2":map.put("INSURANCE_TYPE","短信");break;
                case "3":map.put("INSURANCE_TYPE","通用");break;
                default:map.put("INSURANCE_TYPE","无");break;
            }
        }
        return list;
    }

    /**
     * 添加表头第一行
     */
    public static Map<String,String> TOP=new HashMap<String,String>(){
        {
            put("AGENT_NUM","机构编码");
            put("AGENT_NAME","机构名称");
            put("AMOUNT","金额");
            put("TYPE","类型");
            put("INSURANCE_TYPE","增值服务类型");
            put("CREATE_DATE","创建日期");
            put("CREATE_TIME","创建时间");
            put("CREATE_NAME","创建人");
            put("NOTE","备注");
        }
    };

    /**
     * 添加增值服务统计表头第一行
     */
    public static Map<String,String> TOP_STATISTICS=new HashMap<String,String>(){
        {
            put("AGENT_NUM","机构编码");
            put("AGENT_NAME","机构名称");
            put("SUMAMOUNT","总金额");
            put("TYPE","类型");
            put("INSURANCE_TYPE","增值服务类型");
            put("CREATE_DATE","创建日期");
            put("COUNTNUM","总数");
        }
    };



    /**
     * 将getPageList中的list的金额进行处理
     * @param list
     * @return
     */
    /*public List processingGetPgeList(List list){
        for (Object o:list){
            //将o强转成map
            Map map=(Map)o;
            if(!Utils.IsNull(map.get("AMOUNT"))){
                BigDecimal amount1 = new BigDecimal(String.valueOf(map.get("AMOUNT")));
                BigDecimal amount = amount1.setScale(2, RoundingMode.HALF_UP);
                map.put("AMOUNT",amount.toString());
            }
        }
        return list;
    }*/
}
