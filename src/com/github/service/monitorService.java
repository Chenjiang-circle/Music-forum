package com.github.service;

import com.github.domain.webData;

/**
 * 该service用来检测各种数据
 */
public interface monitorService {
    /**
     * 当用户访问home页时,网页浏览量加一
     */
    void view();

    /**
     * 获取网站信息
     * @return
     */
    webData getWebData();
}
