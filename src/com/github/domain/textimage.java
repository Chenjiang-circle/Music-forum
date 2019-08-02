package com.github.domain;

public class textimage {
    private String userid;
    private String textimage;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTextimage() {
        return textimage;
    }

    public void setTextimage(String textimage) {
        this.textimage = textimage;
    }

    @Override
    public String toString() {
        return "textimage{" +
                "userid='" + userid + '\'' +
                ", textimage='" + textimage + '\'' +
                '}';
    }
}
