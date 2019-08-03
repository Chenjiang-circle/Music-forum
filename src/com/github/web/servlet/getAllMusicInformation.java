package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.music;
import com.github.service.MusicService;
import com.github.service.impl.MusicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getAllMusicInformation")
public class getAllMusicInformation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MusicService musicService = new MusicServiceImpl();
        List<music> allPassMusics = musicService.getAllPassMusics();
        String s = JSON.toJSONString(allPassMusics);
        System.out.println(s);
        JSON.writeJSONString(resp.getWriter(), allPassMusics);
        System.out.println("发送音乐信息成功!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
