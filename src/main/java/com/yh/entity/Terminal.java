package com.yh.entity;

import lombok.Data;

import java.util.Date;

/**
 * ************************************
 * 终端信息表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@Data
public class Terminal {
    private int id;
    private String posno;//终端编号[本地]
    private int pos_type;//终端类型（0：传统终端  1：MPOS  2：智能终端 3：机构终端）
    private String merno;//商户编号
    private int sm_num;//业务员编号
    private int agent_num;//代理商编号
    private int sale_num;//销售员编号
    private int so_num;//所属营业部
    private int status;//终端状态 0：启用 1：关停 2：绑定未开通
    private String createman;//添加人
    private Date createdate;//添加时间
    private String note;//备注
    private int isdetele;//0 未删除（可见） 1 删除 （不可见）
    private int bind_type;//绑定类型  1 首次绑定 2 增机
    private String machine_num;//机器号
}
