package com.github.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *查询已经follow过的人 (个人主页显示)
 */

@WebServlet("/getfollow")
public class getfollow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        String userid = request.getParameter("userid");
        List<User> allFollowedUser = userService.getAllFollowedUser(userid);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), allFollowedUser);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
