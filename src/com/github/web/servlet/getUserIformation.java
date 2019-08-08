package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/getUserIformation")
public class getUserIformation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User usermsg = (User) session.getAttribute("usermsg");
        System.out.println(usermsg);
        JSON.writeJSONString(resp.getWriter(), usermsg);
        System.out.println("页面加载时向页面发送是否登录以及登录信息");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
