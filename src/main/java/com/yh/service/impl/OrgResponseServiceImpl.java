package com.yh.service.impl;

import com.yh.dao.OrgResponseDao;
import com.yh.entity.OrgResponse;
import com.yh.entity.Page;
import com.yh.service.OrgResponseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrgResponseServiceImpl extends BaseServiceImpl<OrgResponse, Integer>
        implements OrgResponseService {

    @Resource
    private OrgResponseDao responseDao;

    @Override
    public void getPageList(Page page) {
        
        responseDao.getPageList(page);
    }
}
