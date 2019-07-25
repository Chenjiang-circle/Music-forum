package com.github.service.impl;

import com.github.dao.TextDao;
import com.github.dao.impl.TextDaoImpl;
import com.github.domain.comment;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextServiceImplTest {

    @Test
    public void createText() {
    }

    @Test
    public void addComment() {
        TextDao textDao = new TextDaoImpl();
        comment comments = new comment();
        comments.setTextid(19);
        comments.setUserid("1455075085@qq.com");
        comments.setTime("2019-8-2");
        comments.setText("测试插入评论1的子评论2");
        int commentid = textDao.addCommentToText(comments);
        if (commentid != -1){
            Boolean aBoolean = textDao.addComment(comments, commentid);
            System.out.println(aBoolean);
        }else {
            System.out.println("存储评论到数据库错误，报错点（TextServiceImpl-->addComment）");
        }
    }
}