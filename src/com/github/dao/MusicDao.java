package com.github.dao;

import com.github.domain.music;

import java.util.List;

public interface MusicDao {
    /**
     * 此方法用来将音乐的名称(原始上传文件名)\音乐id(生成的UUID)\音乐网络路径(http://172.20.....)\音乐类型\音乐上传时间,存储在数据库中
     * @param amusic
     * @return 返回true说明存储成功
     */
    Boolean uploadMusic(music amusic);

    /**
     * 此方法用来获取所有未审核的音乐
     * @return
     */
    List<music> getNotPassMusics();

    /**
     * 此方法用来使音乐通过审核
     * @param url
     * @return
     */
    Boolean pass(String url, String verifier);

    /**
     * 此方法用来使音乐不通过审核
     * @param url
     * @param verifier
     * @return
     */
    Boolean notpass(String url, String verifier);

    /**
     * 此方法用来获取所有的已通过审核的音乐信息
     * @return 返回音乐实体类集合
     */
    List<music> getAllPassMusics();

    /**
     * 此方法用来获取排行榜
     * @param wherefrom
     * @return
     */
    String getRankingList(String wherefrom);

    /**
     * 获取所有管理员同意上传到首页的文章
     * @return
     */
    List<music> getCanToHomeArtivcle();
}
