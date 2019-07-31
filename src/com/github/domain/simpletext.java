package com.github.domain;

public class simpletext {
    private int textid;
    private String title;
    private String textimage;

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

    @Override
    public String toString() {
        return "simpletext{" +
                "textid=" + textid +
                ", title='" + title + '\'' +
                ", textimage='" + textimage + '\'' +
                '}';
    }
}
