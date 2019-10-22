package com.yh.service;

import com.yh.entity.Page;
import com.yh.entity.Terminal;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * ************************************
 * TerminalService
 * @param <T>
 * @param <PK>
 * ************************************
 */
public interface TerminalService extends BaseService<Terminal, Integer> {

    /**
     *
     *********************************************************.<br>
     * [方法] getTerminalList <br>
     * [描述] 终端信息查询 <br>
     * [参数] [Page](对参数的描述) <br>
     * [返回] <br>
     * [日期] 2019/7/9
     * [时间] 10:50
     * [作者] mh
     *********************************************************.<br>
     */
    void getPageList(Page page) throws Exception;

    void getExportExcelList(Map param, HttpServletResponse response) throws Exception;

}
