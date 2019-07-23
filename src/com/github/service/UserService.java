package com.github.service;

import com.github.domain.User;

public interface UserService {
    void register(User users);
    User signin(User users);
    Boolean isHave(User users);
}
