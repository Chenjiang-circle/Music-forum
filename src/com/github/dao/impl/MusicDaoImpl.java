package com.github.dao.impl;

import com.github.dao.MusicDao;
import com.github.domain.music;
import com.github.util.JDBCUtils;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.springframework.jdbc.core.JdbcTemplate;

public class MusicDaoImpl implements MusicDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Boolean uploadMusic(music amusic) {
        try {
            String sql = "insert into music values(?, ?, ?, ?, ?, ?, ?)";
            int update = template.update(sql, amusic.getMusicid(), amusic.getMusicname(), amusic.getUrl(), amusic.getUserid(), amusic.getTime(), amusic.getIspass(), amusic.getVerifier());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("存储音乐信息失败!");
            return false;
        }
    }
}
