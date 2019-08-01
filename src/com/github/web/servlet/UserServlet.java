package com.github.web.servlet;
/**
 * 个人主页
 * @autor lzy
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *
         * 1.获取个人信息（user表所有内容），将其封装json发给前端
         * 2.获取已传的text，comment（标题+封面），传给前端
         * 3....
         *
         **/
        HttpSession session = request.getSession();
        Object user = session.getAttribute("usermsg");
        UserService userService = new UserServiceImpl();
        if(user != null){
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), user);

        }else{
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.print("<script language='javascript'>alert('登录已失效请重新登陆');window.location.href='http://172.20.151.117:8066/Music_forum/login-regist-writeText/enter.html';</script>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
