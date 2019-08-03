package com.github.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.follow;
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

@WebServlet("/followservlet")
public class FollowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object usermsg = session.getAttribute("usermsg");
        if(usermsg != null){
            User user = (User) usermsg;
            /**
             * 查询follow表内容
             */
            follow follow = new follow();
            Map<String, String[]> map = request.getParameterMap();
            try {
                BeanUtils.populate(follow, map);
                //如果前端没有userid
                follow.setUserid(user.getUserid());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Map<String, Object> map1 = new HashMap<String, Object>();
            UserServiceImpl userService = new UserServiceImpl();
            if( ! userService.isFollow(follow) ){
                userService.follow(follow);
                /**
                 * 差一个加一减一操作
                 *
                 */
                map1.put("userid", follow.getUserid());
                map1.put("followed", follow.getFollowed());
                map1.put("success", true);
            }else{
                /**
                 *差一个加一减一，删除数据操作
                 */
                map1.put("userid", follow.getUserid());
                map1.put("followed", follow.getFollowed());
                map1.put("success", false);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),map1);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}