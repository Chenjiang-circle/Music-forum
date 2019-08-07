package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.User;
import com.github.service.TextService;
import com.github.service.impl.TextServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import com.github.domain.comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/changecomment")
public class changecomment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("usermsg");
        if (user != null) {
            try {
                TextService textService = new TextServiceImpl();
                comment comment = new comment();

                Map<String, String[]> map = request.getParameterMap();
                BeanUtils.populate(comment, map);
                comment.setUserid(user.getUserid());

                textService.addComment(comment);

                String s = JSON.toJSONString(comment);
                System.out.println(s);
                JSON.writeJSONString(response.getWriter(),comment );

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
