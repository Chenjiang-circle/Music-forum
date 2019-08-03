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
    private String ispass;
    private String verifier;
    private String musiccover;
    private String album;
    private String songer;
    private String publictime;
    private String company;

    public String getSonger() {
        return songer;
    }

    public void setSonger(String songer) {
        this.songer = songer;
    }

    public String getPublictime() {
        return publictime;
    }

    public void setPublictime(String publictime) {
        this.publictime = publictime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getMusiccover() {
        return musiccover;
    }

    public void setMusiccover(String musiccover) {
        this.musiccover = musiccover;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
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
                ", ispass='" + ispass + '\'' +
                ", verifier='" + verifier + '\'' +
                ", musiccover='" + musiccover + '\'' +
                ", album='" + album + '\'' +
                ", songer='" + songer + '\'' +
                ", publictime='" + publictime + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
