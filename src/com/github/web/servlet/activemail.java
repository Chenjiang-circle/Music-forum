/**
 * FileName: activemail
 * Author:   陈江超
 * Date:     2019/7/21 9:46
 * Description: 邮箱检查
 */
package com.github.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        req.setCharacterEncoding("utf-8");
        // 获取参数
        String mailcode = req.getParameter("mailcode");
        System.out.println("success");
        resp.sendRedirect("/Music_forum/success.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
