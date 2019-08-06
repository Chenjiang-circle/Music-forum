package com.github.service.impl;

import com.github.service.monitorService;
import org.junit.Test;

import static org.junit.Assert.*;

public class monitorServiceImplTest {
    monitorService monitorService = new monitorServiceImpl();

    @Test
    public void view() {
        monitorService.view();
    }
}