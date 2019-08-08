/**
 * @auther lzy
 * 实现功能：
 * 将评论内容发送至前端
 */
package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.text2;
import com.github.service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/comment")
public class comment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("usermsg");
        if (user != null) {
            //int textid = Integer.parseInt(request.getParameter("thistext"));
            int atextid = (int) session.getAttribute("atextid");
            TextServiceImpl textService = new TextServiceImpl();
            text2 text = textService.findAlltext(atextid);
            //传递评论集
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), text.getList());
            String s = JSON.toJSONString(text.getList());
            System.out.println(s);
            //session.removeAttribute("atextid");

        }else{
            //没登录
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
