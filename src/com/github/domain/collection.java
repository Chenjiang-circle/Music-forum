/**
 * FileName: collection
 * Author:   陈江超
 * Date:     2019/7/27 16:15
 * Description: 用户收藏文章
 */
package com.github.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户收藏文章〉
 *
 * @author 陈江超
 * @create 2019/7/27
 * @since 1.0.0
 */
public class collection {
    private String userid;
    private int collectiontextid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getCollectiontextid() {
        return collectiontextid;
    }

    public void setCollectiontextid(int collectiontextid) {
        this.collectiontextid = collectiontextid;
    }

    @Override
    public String toString() {
        return "collection{" +
                "userid='" + userid + '\'' +
                ", collectiontextid=" + collectiontextid +
                '}';
    }
}
