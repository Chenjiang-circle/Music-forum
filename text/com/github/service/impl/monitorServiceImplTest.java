package com.github.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.domain.webData;
import com.github.service.monitorService;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class monitorServiceImplTest {
    monitorService monitorService = new monitorServiceImpl();

    @Test
    public void view() {
        //monitorService.view();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        Date date = new Date();
        String format1 = format.format(date);
        System.out.println(format1);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        String yesterday = new SimpleDateFormat( "MM-dd ").format(cal.getTime());
        System.out.println(yesterday);
    }

    @Test
    public void getWebData() {
        webData webData = monitorService.getWebData();
        String s = JSON.toJSONString(webData);
        System.out.println(s);
    }
}