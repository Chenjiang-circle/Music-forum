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
}
