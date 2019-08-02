package com.github.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.domain.music;
import com.github.service.MusicService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MusicServiceImplTest {
    private MusicService musicService = new MusicServiceImpl();

    @Test
    public void uploadMusic() {
        music amusic = new music();
        amusic.setMusicid(123456);
        amusic.setMusicname("小跳蛙");
        amusic.setTime("2008-9-6");
        amusic.setUrl("http://172.20.151.112:8088/123.mp3");
        amusic.setUserid("1455075085@qq.com");
        Boolean aBoolean = musicService.uploadMusic(amusic);
        System.out.println(aBoolean);
    }

    @Test
    public void getNotPassMusics() {
        List<music> notPassMusics = musicService.getNotPassMusics();
        String s = JSON.toJSONString(notPassMusics);
        System.out.println(s);
    }
}