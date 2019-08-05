package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.service.MusicService;
import com.github.service.impl.MusicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getRankingList")
public class getRankingList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String wherefrom = req.getParameter("wherefrom");

        MusicService musicService = new MusicServiceImpl();
        String rankingList = musicService.getRankingList(wherefrom);
        JSON.writeJSONString(resp.getWriter(), rankingList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
