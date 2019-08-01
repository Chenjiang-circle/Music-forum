/**
 * 登录
 * @autor lzy
 */
package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
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
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signinservlet")
public class signin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //user 对象
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //验证用户名密码是否匹配
        UserService userService = new UserServiceImpl();
        Boolean have = userService.isHave(user);
        System.out.println("have = "+have);
        User signin = userService.signin(user);
        //设置响应的数据格式为json
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> map1 = new HashMap<String, Object>();
        if (have == true && signin != null) {
            //成功
            map1.put("success", true);
            map1.put("userid", user.getUserid());
            map1.put("password", user.getPassword());
            //1.获取session
            HttpSession session = request.getSession();
            //2.存储数据
            session.setAttribute("usermsg", user);
        }else {
            map1.put("success", false);
            map1.put("userid", user.getUserid());
            map1.put("password", user.getPassword());
        }
        //将map转为json
        String s = JSON.toJSONString(map1);
        System.out.println(s);
        ObjectMapper mapper = new ObjectMapper();
        //传递给客户端
        mapper.writeValue(response.getWriter(), map1);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}