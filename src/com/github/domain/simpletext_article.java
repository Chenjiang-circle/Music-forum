package com.github.domain;

public class simpletext_article {
    private int textid;
    private String title;
    private String textimage;
    private int likes;
    private int comment;
    private String type;

    public int getTextid() {
        return textid;
    }

    public void setTextid(int textid) {
        this.textid = textid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextimage() {
        return textimage;
    }

    public void setTextimage(String textimage) {
        this.textimage = textimage;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "simpletext_article{" +
                "textid=" + textid +
                ", title='" + title + '\'' +
                ", textimage='" + textimage + '\'' +
                ", likes=" + likes +
                ", comment=" + comment +
                ", type='" + type + '\'' +
                '}';
    }
}
