package com.yh.service;

import com.yh.entity.OrgResponse;
import com.yh.entity.Page;

@SuppressWarnings("all")
public interface OrgResponseService extends BaseService<OrgResponse, Integer> {

    void getPageList(Page page);
}
