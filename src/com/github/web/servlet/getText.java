/**
 * Author:   lzy
 * Description: 获取文章
 */
package com.github.web.servlet;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.collection;
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
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getText")
public class getText extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usermsg");

        System.out.println("user -----> " + user);
        Map<String, Object> map = new HashMap<String, Object>();

        TextServiceImpl textService = new TextServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        Boolean ifColl = false;
        int atextid = (int) session.getAttribute("atextid");
        System.out.println("要打印的textid is " + atextid);
        text2 text = textService.findAlltext(atextid);

        if (user != null) {
            //返回text对象和collection对象（用于显示按钮）
            collection collection = new collection();
            collection.setUserid(user.getUserid());
            collection.setCollectiontextid(atextid);
            System.out.println(collection.toString());
            ifColl = userService.isCollection(collection);
        }
        map.put("ifColl", ifColl);
        map.put("user", user);
        map.put("text", text);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), map);
        String s = JSON.toJSONString(map);
        System.out.println(s);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
