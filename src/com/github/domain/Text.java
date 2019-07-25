/**
 * FileName: Text
 * Author:   陈江超
 * Date:     2019/7/22 11:27
 * Description: 文章
 */
package com.github.domain;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈文章〉
 *
 * @author 陈江超
 * @create 2019/7/22
 * @since 1.0.0
 */
public class Text {
    private String userid;
    private Integer textid = null;
    private String time;
    private int likes;
    private int comment;
    private int collection;
    private String text;
    private String title;
    private String type;


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

    @Override
    public String toString() {
        return "Text{" +
                "userid='" + userid + '\'' +
                ", textid=" + textid +
                ", time='" + time + '\'' +
                ", likes=" + likes +
                ", comment=" + comment +
                ", collection=" + collection +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
