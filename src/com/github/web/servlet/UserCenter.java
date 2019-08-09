package com.github.web.servlet;
/**
 * 个人主页
 *
 * @autor lzy
 */

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.follow;
import com.github.domain.simpletext;
import com.github.service.TextService;
import com.github.service.UserService;
import com.github.service.impl.TextServiceImpl;
import com.github.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/usercenter")
public class UserCenter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> map = new HashMap<String, Object>();
        TextServiceImpl textService = new TextServiceImpl();
        UserService userService = new UserServiceImpl();
        //获取session数据

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("usermsg");
        User auser = null;
        if (user != null) {
            System.out.println("个人中心session 的 userid : " + user.getUserid());
            auser = userService.getUserByUserID(user.getUserid());
            session.setAttribute("usermsg", auser);
        }


        //接收userid

        String auserid = (String) session.getAttribute("auserid");


        List<simpletext> listArtical = textService.getsimpleTextByUserID(auserid);
        List<simpletext> listCollection = textService.getcollectionByUserID(auserid);

        //查询user信息
        System.out.println("个人中心 接收的 userid"+auserid);
        User userByUserID = userService.getUserByUserID(auserid);
        System.out.println("通过个人中心接受的userid查询到的user对象是：" + userByUserID);


        //用于传递list集合，将list集合再放入一个json中传给前端

        String isself = "";
        String isfollow = "false";

        //获取follownum
        int follownum = userService.countFollowedNumByUserId(auserid);

        if (user != null) {
            if (auserid.equals(user.getUserid())) {
                //判断是否为自己的主页
                isself = "true";
                map.put("user", auser);

            } else {

                //判断是否为他人主页
                isself = "false";

                // 根据userid查询数据
                map.put("user", userByUserID);

                //判断关注情况
                follow follow = new follow();
                follow.setUserid(user.getUserid());
                follow.setFollowed(auserid);
                if (userService.isFollow(follow))
                    isfollow = "true";
                else
                    isfollow = "false";


            }
            //传递list集合
            map.put("listArticle", listArtical);
            map.put("listCollection", listCollection);
            map.put("isself", isself);
            map.put("isfollow", isfollow);
            map.put("follownum", follownum);
            map.put("username", auser.getUsername());
            map.put("userid", auser.getUserid());
            map.put("imageid", auser.getImageid());
            String s = JSON.toJSONString(map);
            System.out.println(s);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), map);
            //session.removeAttribute("auserid");

        } else {
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.print("<script language='javascript'>alert('登录已失效请重新登陆');window.location.href='http://172.20.151.117:8066/Music_forum/login-regist-writeText/enter.html';</script>");
            isself = "false";
            map.put("isself", isself);
            map.put("isfollow", isfollow);
            map.put("listArticle", listArtical);
            map.put("listCollection", listCollection);
            map.put("follownum", follownum);
            map.put("user", userByUserID);
            String s = JSON.toJSONString(map);
            System.out.println(s);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), map);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
