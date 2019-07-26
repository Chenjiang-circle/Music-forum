/**
 * FileName: image
 * Author:   陈江超
 * Date:     2019/7/25 9:00
 * Description: 图片
 */
package com.github.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈图片〉
 *
 * @author 陈江超
 * @create 2019/7/25
 * @since 1.0.0
 */
public class image {
    private String imageid;
    private String url;

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "image{" +
                "imageid='" + imageid + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
