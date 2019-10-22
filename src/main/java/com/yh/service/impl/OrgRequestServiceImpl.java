package com.yh.service.impl;

import com.yh.dao.OrgRequestDao;
import com.yh.entity.OrgRequest;
import com.yh.entity.Page;
import com.yh.service.OrgRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrgRequestServiceImpl extends BaseServiceImpl<OrgRequest, Integer>
        implements OrgRequestService {

    @Resource
    private OrgRequestDao requestDao;

    @Override
    public void getPageList(Page page) {
        requestDao.getPageList(page);
    }
}
