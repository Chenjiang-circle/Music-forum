package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.music;
import com.github.service.MusicService;
import com.github.service.impl.MusicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class home_getMusic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        MusicService musicService = new MusicServiceImpl();
        List<music> allPassMusics = musicService.getAllPassMusics();
        Map<String, Object> map = new HashMap<>();
        HttpSession session = req.getSession();
        Object attribute = session.getAttribute("usermsg");
        map.put("Musics", allPassMusics);
        map.put("user", attribute);
        JSON.writeJSONString(resp.getWriter(), map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
