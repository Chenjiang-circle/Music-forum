package com.github.service;

import com.github.domain.User;
import com.github.domain.follow;

public interface UserService {
    void register(User users);
    User signin(User users);
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
}
