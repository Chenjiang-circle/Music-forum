/**
 * FileName: activemail
 * Author:   陈江超
 * Date:     2019/7/21 9:46
 * Description: 邮箱检查
 */
package com.github.web.servlet;

import com.github.domain.User;
import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;
import com.github.util.toMD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈邮箱检查〉
 *
 * @author 陈江超
 * @create 2019/7/21
 * @since 1.0.0
 */
@WebServlet("/activemailServlet")
public class activemail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 获取参数
        String mailcode = req.getParameter("mailcode");
        String receiveMail = req.getParameter("receiveMail");
        System.out.println("注册用户激活链接接受到的邮箱是：" + receiveMail);
        HttpSession session = req.getSession();
        User attribute = (User) session.getAttribute(receiveMail);
        String userid = attribute.getUserid();
        String s = toMD5.MD5(userid);
        if (s.equals(mailcode)){
            String password = attribute.getPassword();
            System.out.println("写入数据库之间的密码：" + password);
            String s1 = toMD5.MD5(password);
            attribute.setPassword(s1);
            System.out.println("写入数据库的密码：" + attribute.getPassword());
            attribute.setImageid("http://172.20.151.112:8088/12d5a851d30a4748ae71dbe9164d6ab4.jpg");
            UserService userService = new UserServiceImpl();
            userService.register(attribute);
            resp.sendRedirect("/Music_forum/success.html");
        }else{
            resp.sendRedirect("/Music_forum/failRegister.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
