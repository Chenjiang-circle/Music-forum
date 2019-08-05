package com.github.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.text2;
import com.github.service.TextService;
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
import java.util.Map;

@WebServlet("/collect")
public class collect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Utf-8");
        try {
            String textid = request.getParameter("collectiontextid");
            int ifColl = Integer.parseInt(request.getParameter("ifColl"));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("usermsg");
            TextService textService= new TextServiceImpl();
            text2 text = textService.findAlltext(1);
            Map<String, String[]> map = request.getParameterMap();
            collection collection = new collection();
            BeanUtils.populate(collection,map);
            collection.setUserid(user.getUserid());
            System.out.println(collection+"----------");
            if(ifColl == 1){
                UserServiceImpl userService = new UserServiceImpl();
                userService.addCollectionText(collection);
            }else{
                //取消收藏
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
