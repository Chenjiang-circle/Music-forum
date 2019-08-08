package com.github.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.dao.UserDao;
import com.github.dao.impl.UserDaoImpl;
import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.follow;
import com.github.service.UserService;
import org.junit.Test;

import java.util.List;


public class UserServiceImplTest {
    UserDao userDao = new UserDaoImpl();
    UserService userService = new UserServiceImpl();

    @Test
    public void register() {
        User user = new User();
        user.setUserid("2464792469@qq.com");
        user.setPassword("123456");
        user.setUsername("xyz");
        user.setSex("男");
        user.setImageid("123");
        user.setDescription("大家好， 我是...");
        user.setFans(12);
        user.setNumsignin(101);
        userDao.register(user);
    }

    @Test
    public void signin() {
        User user = new User();
        user.setUserid("root@qq.com");
        user.setPassword("123456");
        UserService userService = new UserServiceImpl();
        User signin = userService.signin(user);
        System.out.println(signin);
    }

    @Test
    public void isHave() {
    }

    @Test
    public void follow() {
        follow follows = new follow();
        follows.setUserid("1455075085@qq.com");
        follows.setFollowed("1517742603@qq.com");
        userDao.follow(follows);
    }

    @Test
    public void isFollow() {
        follow follows = new follow();
        follows.setUserid("1455075085@qq.com");
        follows.setFollowed("root@qq.com");
        Boolean follow = userDao.isFollow(follows);
        System.out.println(follow);
    }

    @Test
    public void addCollectionText() {
        collection colllection = new collection();
        colllection.setUserid("1455075085@qq.com");
        colllection.setCollectiontextid(15);
        UserService userService = new UserServiceImpl();
        Boolean aBoolean = userService.addCollectionText(colllection);
        System.out.println(aBoolean);
    }

    @Test
    public void isCollection() {
        collection colllection = new collection();
        colllection.setUserid("1455075085@qq.com");
        colllection.setCollectiontextid(15);
        UserService userService = new UserServiceImpl();
        Boolean collection = userService.isCollection(colllection);
        System.out.println(collection);
    }

    @Test
    public void addCheckin() {
        Boolean aBoolean = userDao.addCheckin("1455075085@qq.com");
        System.out.println(aBoolean);
    }

    @Test
    public void cancelFollow() {
        follow follow = new follow();
        follow.setUserid("1455075085@qq.com");
        follow.setFollowed("1517742603@qq.com");
        Boolean aBoolean = userService.cancelFollow(follow);
        System.out.println(aBoolean);
    }

    @Test
    public void getUserByUserID() {
        User userByUserID = userService.getUserByUserID("1455075085@qq.com");
        String s = JSON.toJSONString(userByUserID);
        System.out.println(s);
    }

    @Test
    public void getAllFollowedUser() {
        List<User> allFollowedUser = userService.getAllFollowedUser("1455075085@qq.com");
        String s = JSON.toJSONString(allFollowedUser);
        System.out.println(s);
    }

    @Test
    public void countFollowedNumByUserId() {
        int i = userService.countFollowedNumByUserId("1455075085@qq.com");
        System.out.println(i);
    }
}