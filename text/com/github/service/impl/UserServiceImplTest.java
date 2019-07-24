package com.github.service.impl;


import com.github.dao.UserDao;
import com.github.dao.impl.UserDaoImpl;
import com.github.domain.follow;
import org.junit.Test;


public class UserServiceImplTest {

    @Test
    public void register() {
    }

    @Test
    public void signin() {
    }

    @Test
    public void isHave() {
    }

    @Test
    public void follow() {
        follow follows = new follow();
        follows.setUserid("1455075085@qq.com");
        follows.setFollowed("2464792469@qq.com");
        UserDao userDao = new UserDaoImpl();
        userDao.follow(follows);
    }

    @Test
    public void isFollow() {
        follow follows = new follow();
        follows.setUserid("1455075085@qq.com");
        follows.setFollowed("2464792469@qq.com");
        UserDao userDao = new UserDaoImpl();
        Boolean follow = userDao.isFollow(follows);
        System.out.println(follow);
    }
}