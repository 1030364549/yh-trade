package com.yh.entity;

import lombok.Data;
/**
 * ************************************
 * 机构商户表
 * @param <T>
 * @param <PK>
 * ************************************
 */
@Data
public class Merchant {
    private int id;
    private String merno;//商户编号
    private String mer_name;//商户名称
    private String mer_category;//商户类别 0 标准类 1 优惠类 2 减免类
    private String mer_address;//商户详细地址
    private String legal_name;//法人姓名
    private String legal_cer;//法人证件类型 0 身份证 1 护照
    private String legal_cerno;//法人证件类型号码
    private String legal_phone;//法人联系方式
    private String mer_phone;//商户手机号
    private String mer_status;//商户状态 0 禁用 1 启用
    private String agent_num;//商户所属代理商
    private String add_date;//添加日期
    private String add_time;//添加时间
    private String serial;//系统流水号
    private String org_merno;//机构原商户号
    private String mer_sett;//商户结算周期 0 T0 1 D1
    private String picture;//商户上传图片 0 未上传 1已上传
}
