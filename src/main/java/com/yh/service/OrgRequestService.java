package com.yh.service;

import com.yh.entity.OrgRequest;
import com.yh.entity.Page;

@SuppressWarnings("all")
public interface OrgRequestService  extends BaseService<OrgRequest, Integer> {

    void getPageList(Page page);
}
