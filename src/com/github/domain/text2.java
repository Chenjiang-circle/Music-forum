/**
 * FileName: text2
 * Author:   陈江超
 * Date:     2019/7/27 19:34
 * Description: 盖楼
 */
package com.github.domain;

import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br>
 * 〈盖楼〉
 *
 * @author 陈江超
 * @create 2019/7/27
 * @since 1.0.0
 */
public class text2 {
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
    private ArrayList<text2> list = null;

    public String getTextimage() {
        return textimage;
    }

    public void setTextimage(String textimage) {
        this.textimage = textimage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getTextid() {
        return textid;
    }

    public void setTextid(Integer textid) {
        this.textid = textid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<text2> getList() {
        return list;
    }

    public void setList(ArrayList<text2> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "text2{" +
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
                ", list=" + list +
                '}';
    }
}
