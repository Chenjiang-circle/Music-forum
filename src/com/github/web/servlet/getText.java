/**
 * FileName: getText
 * Author:   陈江超
 * Date:     2019/7/25 21:43
 * Description: 获取文章
 */
package com.github.web.servlet;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.text1;
import com.github.domain.text2;
import com.github.service.impl.TextServiceImpl;
import com.github.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getText")
public class getText extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("usermsg");

            System.out.println("user -----> " + user);
            Map<String, Object> map = new HashMap<String, Object>();

            Boolean isColl = true;
            if (user != null) {
//              int textid = Integer.parseInt(req.getParameter("textid"));
                TextServiceImpl textService = new TextServiceImpl();
                UserServiceImpl userService = new UserServiceImpl();

                //返回text对象

                text2 text = textService.findAlltext(1);
                collection collection = new collection();
                Map<String, String[]> map1 = req.getParameterMap();
                BeanUtils.populate(collection, map1);
                collection.setUserid(user.getUserid());
                System.out.println(collection.toString());
                isColl = userService.isCollection(collection);
                map.put("isColl", isColl);
                map.put("user", user);
                map.put("text", text);
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(resp.getWriter(), map);
                String s = JSON.toJSONString(map);
                System.out.println(s);
            } else {

                //木有登录

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
