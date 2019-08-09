
/**
 * FileName: UserServiceImpl
 * Author:   陈江超
 * Date:     2019/7/21 9:19
 * Description: 用户注册
 */
package com.github.service.impl;

import com.github.dao.UserDao;
import com.github.dao.impl.UserDaoImpl;
import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.follow;
import com.github.service.UserService;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户注册〉
 *
 * @author 陈江超
 * @create 2019/7/21
 * @since 1.0.0
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void register(User users) {
        userDao.register(users);
    }

    @Override
    public User signin(User users) {
        User user1 = userDao.signin(users);
        return user1;
    }

    @Override
    public Boolean isHave(User users) {
        return userDao.isHave(users);
    }

    @Override
    public void follow(follow followers) {
        userDao.follow(followers);
    }

    @Override
    public Boolean isFollow(follow follows) {
        return userDao.isFollow(follows);
    }

    @Override
    public User findUserByTextId(int textid) {
        return userDao.findUserByTextId(textid);
    }

    @Override
    public Boolean addCollectionText(collection collection) {
        return userDao.addCollectionText(collection);
    }

    @Override
    public Boolean isCollection(collection collection) {
        return userDao.isCollection(collection);
    }

    @Override
    public Boolean addCheckin(String textid) {
        return userDao.addCheckin(textid);
    }

    @Override
    public Boolean cancelFollow(follow follow) {
        return userDao.cancelFollow(follow);
    }

    @Override
    public User getUserByUserID(String userid) {

        return userDao.getUserByUserID(userid);
    }

    @Override
    public List<User> getAllFollowedUser(String userid) {
        return userDao.getAllFollowedUser(userid);
    }

    @Override
    public int countFollowedNumByUserId(String userid) {
        return userDao.countFollowedNumByUserId(userid);
    }

    @Override
    public Boolean changeUserimage(String url, String userid) {
        Boolean aBoolean = userDao.changeUserimage(url, userid);
        return aBoolean;
    }
}
