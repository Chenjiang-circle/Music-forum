/**
 * FileName: register
 * Author:   陈江超
 * Date:     2019/7/21 8:57
 * Description: 注册用户servlet
 */
package com.github.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;
import com.github.util.MailUtil;
import com.github.util.toMD5;
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

/**
 * 〈一句话功能简述〉<br>
 * 〈注册用户servlet〉
 *
 * @author 陈江超 lzy
 * @create 2019/7/21
 * @since 1.0.0
 */
@WebServlet("/registerservlet")
public class register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
//        System.out.println("jdoias");
        req.setCharacterEncoding("utf-8");

        Map<String, String[]> map = req.getParameterMap();
        User users = new User();
        try {
            BeanUtils.populate(users, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(users.toString());

        // 判断用户是否存在
        UserService userService = new UserServiceImpl();

        Boolean have = userService.isHave(users);

        if (have) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("success", false);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), map1);
        } else {
            // 先返回给前端true,让前段跳转页面
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("success", true);
            System.out.println("success");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), map1);
            // 再在后台向注册用户发送邮件
            String password = users.getPassword();
            System.out.println("原始密码："+ password);
            // 密码一级加密
            String s = toMD5.MD5(password);
            users.setPassword(s);
            System.out.println("一级加密后的密码：" + users.getPassword());
            // 保存session，键为用户注册的邮箱
            HttpSession session = req.getSession();
            session.setAttribute(users.getUserid(), users);
            //userService.register(users);
            String userid = users.getUserid();
            //String receiveMailAccount = "1455075085@qq.com";
            // 设置一个激活码
            String mailActiveCode = toMD5.MD5(userid);
            try {
                MailUtil.sendActiveMail(userid, mailActiveCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}