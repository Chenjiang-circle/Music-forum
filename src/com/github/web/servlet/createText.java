package com.github.web.servlet;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.Text;
import com.github.domain.textimage;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/createText")
public class createText extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> map = req.getParameterMap();
        Text text = new Text();
        System.out.println(text.toString());
        try {
            BeanUtils.populate(text, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        text.setUserid("1455075085@qq.com");
        System.out.println(text.toString());
        textimage textimage = new textimage();
        try {
            BeanUtils.populate(textimage, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(textimage.toString());
        String s = "{\"success\" : \"true\"}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), s);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
