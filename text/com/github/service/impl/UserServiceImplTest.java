package com.github.service.impl;


import com.github.dao.UserDao;
import com.github.dao.impl.UserDaoImpl;
import com.github.domain.collection;
import com.github.domain.follow;
import com.github.service.UserService;
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

    @Test
    public void addCollectionText() {
        collection colllection = new collection();
        colllection.setUserid("1455075085@qq.com");
        colllection.setCollectiontextid(23);
        UserDao userDao = new UserDaoImpl();
        Boolean aBoolean = userDao.addCollectionText(colllection);
        System.out.println(aBoolean);
    }

    @Test
    public void isCollection() {
        collection colllection = new collection();
        colllection.setUserid("1455075085@qq.com");
        colllection.setCollectiontextid(23);
        UserService userService = new UserServiceImpl();
        Boolean collection = userService.isCollection(colllection);
        System.out.println(collection);
    }
}