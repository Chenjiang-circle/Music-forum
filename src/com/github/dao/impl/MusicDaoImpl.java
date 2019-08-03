package com.github.dao.impl;

import com.github.dao.MusicDao;
import com.github.domain.music;
import com.github.util.JDBCUtils;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MusicDaoImpl implements MusicDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Boolean uploadMusic(music amusic) {
        try {
            String sql = "insert into music values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int update = template.update(sql, amusic.getMusicname(), amusic.getUrl(), amusic.getUserid(), amusic.getTime(), amusic.getIspass(), amusic.getVerifier(), amusic.getMusiccover(), amusic.getAlbum(), amusic.getSonger(), amusic.getPublictime(), amusic.getCompany());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("存储音乐信息失败!");
            return false;
        }
    }

    @Override
    public List<music> getNotPassMusics() {
        try {
            String sql = "select * from music where ispass = 'null'";
            List<music> musics = template.query(sql, new BeanPropertyRowMapper<music>(music.class));
            return musics;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("没有未审核的音乐!");
            return null;
        }
    }

    @Override
    public Boolean pass(String url, String verifier) {
        try{
            String sql = "update music set ispass = 'pass' where url ='"+url+"'";
            int update = template.update(sql);
            String sql1 = "update music set verifier = '"+verifier+"' where url ='"+url+"'";
            int update1 = template.update(sql1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("设置失败,可能是该音乐不存在");
            return false;
        }
    }

    @Override
    public Boolean notpass(String url, String verifier) {
        try{
            String sql = "update music set ispass = 'notpass' where url ='"+url+"'";
            int update = template.update(sql);
            String sql1 = "update music set verifier = '"+verifier+"' where url ='"+url+"'";
            int update1 = template.update(sql1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("设置失败,可能是该音乐不存在");
            return false;
        }
    }

    @Override
    public List<music> getAllPassMusics() {
        try {
            String sql = "select * from music where ispass='pass'";
            List<music> musics = template.query(sql, new BeanPropertyRowMapper<music>(music.class));
            return musics;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("没有通过审核的音乐");
            return null;
        }
    }
}
