/**
 * FileName: text1
 * Author:   陈江超
 * Date:     2019/7/26 10:02
 * Description: 测试
 */
package com.github.domain;

import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试〉
 *
 * @author 陈江超
 * @create 2019/7/26
 * @since 1.0.0
 */
public class text1 extends text2 {
    private String userid;
    private Integer textid = null;
    private String time;
    private int likes;
    private int comment;
    private int collection;
    private String text;
    private String title;
    private String type;
    private String username;
    private String textimage;

    @Override
    public String getTextimage() {
        return textimage;
    }

    @Override
    public void setTextimage(String textimage) {
        this.textimage = textimage;
    }

    @Override
    public String getUserid() {
        return userid;
    }

    @Override
    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public Integer getTextid() {
        return textid;
    }

    @Override
    public void setTextid(Integer textid) {
        this.textid = textid;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int getLikes() {
        return likes;
    }

    @Override
    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public int getComment() {
        return comment;
    }

    @Override
    public void setComment(int comment) {
        this.comment = comment;
    }

    @Override
    public int getCollection() {
        return collection;
    }

    @Override
    public void setCollection(int collection) {
        this.collection = collection;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "text1{" +
                "userid='" + userid + '\'' +
                ", textid=" + textid +
                ", time='" + time + '\'' +
                ", likes=" + likes +
                ", comment=" + comment +
                ", collection=" + collection +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", textimage='" + textimage + '\'' +
                '}';
    }
}
