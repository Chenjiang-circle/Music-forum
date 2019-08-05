package com.github.web.servlet;

import com.github.service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/like")
public class like extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int likes = Integer.parseInt(request.getParameter("likes"));
        int textid = Integer.parseInt(request.getParameter("textid"));
        System.out.println(likes);
        TextServiceImpl textService = new TextServiceImpl();
        textService.updateLikes(likes, textid);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
