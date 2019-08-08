package com.github.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/jumpPage")
public class jumpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String textid = req.getParameter("thistext");
        String userid = req.getParameter("userid");
        if (textid != null) {
            int i = Integer.parseInt(textid);
            if (i != 0){
                HttpSession session = req.getSession();
                session.setAttribute("atextid", i);
            }
        }

        System.out.println("guyjtguy++++++++"+userid);
        if (userid != null){
            HttpSession session = req.getSession();
            session.setAttribute("auserid", userid);
            System.out.println("设置session " + userid);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
