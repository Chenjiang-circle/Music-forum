/**
 * FileName: register
 * Author:   陈江超
 * Date:     2019/7/21 8:57
 * Description: 注册用户servlet
 */
package com.github.web.servlet;

import com.github.domain.User;
import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;
import com.github.util.MailUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈注册用户servlet〉
 *
 * @author 陈江超
 * @create 2019/7/21
 * @since 1.0.0
 */
@WebServlet("/registerservlet")
public class register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
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
        System.out.println(users.toString());

        // 判断用户是否存在
        UserService userService = new UserServiceImpl();

        Boolean have = userService.isHave(users);
        if (have) {
            System.out.println("存在");
        } else {
            userService.register(users);
            String userid = users.getUserid();
            String receiveMailAccount = "1455075085@qq.com";
            // 随便设置一个激活码
            String mailActiveCode = "123456";
            try {
                MailUtil.sendActiveMail(userid, mailActiveCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("success");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
