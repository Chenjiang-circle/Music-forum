package com.github.service;

import com.github.domain.User;
import com.github.domain.follow;

public interface UserService {

    /**
     * 注册用户
     * @param users
     */
    void register(User users);

    /**
     * 登录，返回用户信息（被封装在Uers对象中）
     * @param users
     * @return
     */
    User signin(User users);

    /**
     * 判断用户是否存在
     * @param users
     * @return
     */
    Boolean isHave(User users);

    /**
     * 该方法用来follow其他用户。follow中的参数，请看domain中的follow类
     * @param followers
     */
    void follow(follow followers);

    /**
     * 该方法用来检查是否已经follow过followed，返回true表示已经follow。反之没有follow
     * @param follows
     * @return
     */
    Boolean isFollow(follow follows);

    /**
     * 该方法通过文章id，来获取对应的作者的所有信息
     * @param textid
     * @return
     */
    User findUserByTextId(int textid);
}
