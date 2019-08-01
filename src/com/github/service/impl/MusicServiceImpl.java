package com.github.service.impl;

import com.github.dao.MusicDao;
import com.github.dao.impl.MusicDaoImpl;
import com.github.domain.music;
import com.github.service.MusicService;

public class MusicServiceImpl implements MusicService {
    MusicDao musicDao = new MusicDaoImpl();

    @Override
    public Boolean uploadMusic(music amusic) {
        return musicDao.uploadMusic(amusic);
    }
}
