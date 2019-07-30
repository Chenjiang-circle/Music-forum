/**
 * FileName: follow
 * Author:   陈江超
 * Date:     2019/7/23 21:27
 * Description: 关注
 */
package com.github.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈关注〉
 *
 * @author 陈江超
 * @create 2019/7/23
 * @since 1.0.0
 */
public class follow {
    /**
     * userid: 用户本人的email
     * followed: 被关注用户的email
     */
    private String userid;
    private String followed;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "follow{" +
                "userid='" + userid + '\'' +
                ", followed='" + followed + '\'' +
                '}';
    }
}
