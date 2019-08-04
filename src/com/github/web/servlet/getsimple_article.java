package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.simpletext_article;
import com.github.service.TextService;
import com.github.service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 该servlet用来处理乐评页的请求,获取数据库中的乐评文章返回给前段
 */

public class getsimple_article extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        TextService textService = new TextServiceImpl();
        List<simpletext_article> allsimpleartcle = textService.getAllsimpleartcle();
        String s = JSON.toJSONString(allsimpleartcle);
        System.out.println(s);
        JSON.writeJSONString(resp.getWriter() ,allsimpleartcle);
    }
}
