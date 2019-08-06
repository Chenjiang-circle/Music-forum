package com.github.dao;

import com.github.domain.webData;

public interface monitorDao {
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
