package com.github.dao;

import com.github.domain.music;

public interface MusicDao {
    /**
     * 此方法用来将音乐的名称(原始上传文件名)\音乐id(生成的UUID)\音乐网络路径(http://172.20.....)\音乐类型\音乐上传时间,存储在数据库中
     * @param amusic
     * @return 返回true说明存储成功
     */
    Boolean uploadMusic(music amusic);
}
