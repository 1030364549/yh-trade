package com.yh.service;

import com.yh.entity.Merchant;
import com.yh.entity.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ************************************
 * MarchantService
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public interface MerchantService extends BaseService<Merchant, Integer> {

    /**
     *
     *********************************************************.<br>
     * [方法] getMerchantList <br>
     * [描述] 商户查询 <br>
     * [参数] [Page](对参数的描述) <br>
     * [返回]<br>
     * [日期] 2019/7/8
     * [时间] 11:50
     * [作者] mh
     *********************************************************.<br>
     */
    public void getMerchantList(Page page)  throws Exception;

    /**
     *
     *********************************************************.<br>
     * [方法] getMerchantList <br>
     * [描述] 变更商户状态 <br>
     * [参数] [String id](对参数的描述) <br>
     * [返回] Map <br>
     * [日期] 2019/7/8
     * [时间] 14:50
     * [作者] mh
     *********************************************************.<br>
     */
    public int change(Map map) throws Exception;

    /**
     *
     *********************************************************.<br>
     * [方法] getSettlement <br>
     * [描述] 商户结算信息查询 <br>
     * [参数] [String id](对参数的描述) <br>
     * [返回] String <br>
     * [日期] 2019/7/8
     * [时间] 17:50
     * [作者] mh
     *********************************************************.<br>
     */
    public Map getSettlement(Map marchant) throws Exception;

    /**
     * 商户工商信息查询
     * @param marchant map
     * @return map
     * @throws Exception e
     */
    Map<String,Object> getMerchantSchedule(Map marchant) throws Exception;

    /**
     * 商户图片压缩包下载
     * @param marchant Map
     * @return Map
     * @throws Exception e
     */
    void downloadImg(Map marchant, HttpServletResponse response) throws Exception;
}
