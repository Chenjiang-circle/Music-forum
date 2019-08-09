package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.User;
import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;

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
        //System.out.println(usermsg);
        if (usermsg != null) {
            UserService userService = new UserServiceImpl();
            User userByUserID = userService.getUserByUserID(usermsg.getUserid());
            session.setAttribute("usermsg", userByUserID);
            JSON.writeJSONString(resp.getWriter(), userByUserID);
        }else {
            JSON.writeJSONString(resp.getWriter(), usermsg);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
