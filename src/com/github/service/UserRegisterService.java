package com.github.service;

import com.github.domain.User;

public interface UserRegisterService {
    void register(User users);
    User signin(User users);
}
