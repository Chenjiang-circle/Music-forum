/**
 * FileName: User
 * Author:   陈江超
 * Date:     2019/7/22 10:29
 * Description: 用户
 */
package com.github.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户〉
 *
 * @author 陈江超
 * @create 2019/7/22
 * @since 1.0.0
 */
public class User {
    private String userid;
    private String username;
    private String password;
    private int imageid;
    private int numsignin;
    private int fans;
    private String description;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public int getNumsignin() {
        return numsignin;
    }

    public void setNumsignin(int numsignin) {
        this.numsignin = numsignin;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "user{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", imageid=" + imageid +
                ", numsignin=" + numsignin +
                ", fans=" + fans +
                ", description='" + description + '\'' +
                '}';
    }
}
