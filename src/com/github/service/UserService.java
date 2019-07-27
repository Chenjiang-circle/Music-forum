package com.github.service;

import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.follow;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.xml.stream.events.Comment;

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

    /**
     * 该方法用来添加收藏文章
     * @param collection
     * @return 添加成功返回true
     */
    Boolean addCollectionText(collection collection);

    /**
     * 该方法用来判断文章是否已经被用户收藏
     * @param collection
     * @return
     */
    Boolean isCollection(collection collection);
}
