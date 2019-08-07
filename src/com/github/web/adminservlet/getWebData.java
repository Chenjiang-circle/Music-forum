package com.github.web.adminservlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.webData;
import com.github.service.impl.monitorServiceImpl;
import com.github.service.monitorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getWebData")
public class getWebData extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        monitorService monitorService = new monitorServiceImpl();
        webData webData = monitorService.getWebData();
        JSON.writeJSONString(resp.getWriter(), webData);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
