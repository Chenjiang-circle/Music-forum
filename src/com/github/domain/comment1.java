/**
 * FileName: comment1
 * Author:   陈江超
 * Date:     2019/7/28 9:23
 * Description: 封装comment表
 */
package com.github.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装comment表〉
 *
 * @author 陈江超
 * @create 2019/7/28
 * @since 1.0.0
 */
public class comment1 {
    private int textid;
    private int commentid;
    private String ctext;
    private String ctime;

    public int getTextid() {
        return textid;
    }

    public void setTextid(int textid) {
        this.textid = textid;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getCtext() {
        return ctext;
    }

    public void setCtext(String ctext) {
        this.ctext = ctext;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "comment1{" +
                "textid=" + textid +
                ", commentid=" + commentid +
                ", ctext='" + ctext + '\'' +
                ", ctime='" + ctime + '\'' +
                '}';
    }
}
