package com.github.web.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.Text;
import com.github.domain.User;
import com.github.service.TextService;
import com.github.service.impl.TextServiceImpl;
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

@WebServlet("/createText")
public class createText extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> map = req.getParameterMap();
        String text1 = req.getParameter("text");
        System.out.println(text1);
        Text text = new Text();
        try {
            BeanUtils.populate(text, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        text.setUserid("2464792469@qq.com");
=======
        HttpSession session = req.getSession();
        User usermsg = (User) session.getAttribute("usermsg");
        //System.out.println(usermsg);
        text.setUserid(usermsg.getUserid());
<<<<<<< HEAD
>>>>>>> de339c74529822d415589358d40f549c373f368c
        System.out.println(text.toString());
=======
        //System.out.println(text.toString());
>>>>>>> a5dfe3e47a73dc7ceecaabbe1935b94b2fbdf506
        TextService textService = new TextServiceImpl();
        textService.createText(text);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("success", true);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), map1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
