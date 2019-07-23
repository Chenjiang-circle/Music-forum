package com.github.dao;

import com.github.domain.User;

public interface UserDao {
    void register(User users);
    User signin(User users);
    Boolean isHave(User users);
}
