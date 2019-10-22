package com.yh.service;

import com.yh.entity.BankExpenditure;
import com.yh.entity.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 交易管理service
 * @author puyiliang
 * @date 2019/7/12 11:53
 */
@SuppressWarnings("all")
public interface BankExpenditureService extends BaseService<BankExpenditure, Integer> {

    void getPageList(String statement,Page page);

    void getExportExcelList(Map param, HttpServletResponse response) throws Exception;

    void getStatisticsExportExcelList(Map param, HttpServletResponse response) throws Exception;
}
