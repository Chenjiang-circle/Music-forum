package com.github.service.impl;

import com.github.dao.MusicDao;
import com.github.dao.impl.MusicDaoImpl;
import com.github.domain.music;
import com.github.service.MusicService;

import java.util.List;

public class MusicServiceImpl implements MusicService {
    MusicDao musicDao = new MusicDaoImpl();

    @Override
    public Boolean uploadMusic(music amusic) {
        return musicDao.uploadMusic(amusic);
    }

    @Override
    public List<music> getNotPassMusics() {
        return musicDao.getNotPassMusics();
    }

    @Override
    public Boolean pass(String url, String verifier) {
        return musicDao.pass(url, verifier);
    }

    @Override
    public Boolean notpass(String url, String verifier) {
        Boolean notpass = musicDao.notpass(url, verifier);
        return notpass;
    }

    @Override
    public List<music> getAllPassMusics() {
        return musicDao.getAllPassMusics();
    }

    @Override
    public String getRankingList(String wherefrom) {
        return musicDao.getRankingList(wherefrom);
    }
}
