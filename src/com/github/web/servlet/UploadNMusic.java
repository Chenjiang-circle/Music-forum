package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.domain.music;
import com.github.service.MusicService;
import com.github.service.impl.MusicServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/uploadMusic")
public class UploadNMusic extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        music music = new music();
        try {
            BeanUtils.populate(music, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(music);
        MusicService musicService = new MusicServiceImpl();
        Boolean aBoolean = musicService.uploadMusic(music);
        if (aBoolean){
            System.out.println(" 上传音乐成功");
            JSON.writeJSONString(resp.getWriter(), aBoolean);
        }else {
            System.out.println("上传音乐失败");
            JSON.writeJSONString(resp.getWriter(), aBoolean);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
