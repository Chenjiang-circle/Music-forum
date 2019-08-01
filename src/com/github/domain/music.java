/**
 * FileName: music
 * Author:   陈江超
 * Date:     2019/7/23 21:24
 * Description: 音乐
 */
package com.github.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈音乐〉
 *
 * @author 陈江超
 * @create 2019/7/23
 * @since 1.0.0
 */

public class music {
    private int musicid;
    private String userid;
    private String musicname;
    private String url;
    private String time;
    private int ispass;

    public int getIspass() {
        return ispass;
    }

    public void setIspass(int ispass) {
        this.ispass = ispass;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMusicid() {
        return musicid;
    }

    public void setMusicid(int musicid) {
        this.musicid = musicid;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "music{" +
                "musicid=" + musicid +
                ", userid='" + userid + '\'' +
                ", musicname='" + musicname + '\'' +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                ", ispass=" + ispass +
                '}';
    }
}
