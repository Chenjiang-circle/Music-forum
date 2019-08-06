package com.github.service.impl;

import com.github.dao.impl.monitorDaoImpl;
import com.github.dao.monitorDao;
import com.github.domain.webData;
import com.github.service.monitorService;

public class monitorServiceImpl implements monitorService {
    monitorDao monitorDao = new monitorDaoImpl();

    @Override
    public void view() {
        monitorDao.view();
    }

    @Override
    public webData getWebData() {
        return monitorDao.getWebData();
    }
}
