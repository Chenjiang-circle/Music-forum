package com.github.web.servlet;

import com.github.domain.User;
import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.impl.SessionImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changeUserImage")
public class changeUserImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String newAvatar = req.getParameter("newAvatar");
        String useridAva = req.getParameter("useridAva");
        System.out.println(newAvatar + "       " + useridAva);
        UserService userService = new UserServiceImpl();
        Boolean aBoolean = userService.changeUserimage(newAvatar, useridAva);
        HttpSession session = req.getSession();
        session.setAttribute("usermsg", userService.getUserByUserID(useridAva));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
