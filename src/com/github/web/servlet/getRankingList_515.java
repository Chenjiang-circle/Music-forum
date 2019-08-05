package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.simpletext_article;
import com.github.service.TextService;
import com.github.service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getRankingList515")
public class getRankingList_515 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        TextService textService = new TextServiceImpl();
        List<simpletext_article> topArticle = textService.getTopArticle();
        String s = JSON.toJSONString(topArticle);
        System.out.println(s);
        JSON.writeJSONString(resp.getWriter(), topArticle);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
