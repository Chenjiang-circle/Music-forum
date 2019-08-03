package com.github.web.adminservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/enterservlet")
public class enterservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");

        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        User signin = userService.signin(user);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("pass", true);
        map1.put("userid", user.getUserid());
        map1.put("url", "http://172.20.151.112:8088/8caa11ca1c694299b04f3520b177163f.jpg");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), map1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
