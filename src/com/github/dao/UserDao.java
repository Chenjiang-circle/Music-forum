package com.github.dao;

import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.follow;

import java.util.List;

public interface UserDao {
    void register(User users);
    User signin(User users);
    Boolean isHave(User users);
    void follow(follow followers);
    Boolean isFollow(follow follows);
    User findUserByTextId(int textid);
    Boolean addCollectionText(collection collection);
    Boolean isCollection(collection collection);
    Boolean addCheckin(String userid);
    Boolean cancelFollow(follow follow);
    /**
     * 此方法通过userid获取到对应的用户信息
     * @param userid
     * @return
     */
    User getUserByUserID(String userid);

    /**
     * 此方法通过用户id获取到他关注的所有人的信息
     * @param userid
     * @return
     */
    List<User> getAllFollowedUser(String userid);

    /**
     * 此方法通过用户id获取到他关注的总人数
     * @param userid
     * @return
     */
    int countFollowedNumByUserId(String userid);

    /**
     * 此方法用来更换用户头像
     * @param url
     * @param userid
     * @return
     */
    Boolean changeUserimage(String url, String userid);
}
