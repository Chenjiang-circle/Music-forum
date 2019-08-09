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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/getSearchReasult")
public class getSearchReasult extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String search = (String) session.getAttribute("search");
        System.out.println("得到的search为" + search);
        List<simpletext_article> simpletext_articles = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if (search != null) {
            TextService textService = new TextServiceImpl();
            simpletext_articles = textService.searchArticleByKeyWorks(search);
        }
        map.put("texts", simpletext_articles);
        map.put("keyworks", search);
        String s = JSON.toJSONString(map);
        System.out.println(s);
        JSON.writeJSONString(resp.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
