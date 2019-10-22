package com.yh.service;
import com.yh.entity.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
/**
 * @Author: Liyh
 * @Date: 2019/10/15 9:00
 * @Description:
 * @Version: 1.0
 */
@SuppressWarnings("all")
public interface IncrementLogService {
    void getPageList(Page page) throws Exception;

    void getPageList(String statment,Page page) throws Exception;

    void getExportExcelList(Map param, HttpServletResponse response)throws Exception;

    void getExportExcelListStatistics(Map param, HttpServletResponse response)throws Exception;

}
