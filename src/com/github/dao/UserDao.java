package com.github.dao;

import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.follow;

public interface UserDao {
    void register(User users);
    User signin(User users);
    Boolean isHave(User users);
    void follow(follow followers);
    Boolean isFollow(follow follows);
    User findUserByTextId(int textid);
    Boolean addCollectionText(collection collection);
    Boolean isCollection(collection collection);
}
