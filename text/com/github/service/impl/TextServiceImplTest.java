package com.github.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dao.TextDao;
import com.github.dao.impl.TextDaoImpl;
import com.github.domain.*;
import com.github.service.TextService;
import org.junit.Test;

import java.util.List;


public class TextServiceImplTest {
    private TextDao textDao = new TextDaoImpl();
    TextService textService = new TextServiceImpl();

    @Test
    public void createText() {
        Text text = new Text();
        text.setUserid("1455075085@qq.com");
        text.setText("哲理文章" +
                "　　我们才想起了采撷花朵，可是我们已经错过了花期；汽车开动了，我们才姗姗来迟；当那个朝思暮想的人就站在面前的时候，我们却茫然地让其擦肩而过……\n" +
                "\n" +
                "　　错过了花期，错过了出发，错过了佳人……人生中该有多少错过啊！\n" +
                "\n" +
                "　　说好了要珍惜，明知道要把握，为什么还要一而再，再而三地错过呢？人生越长，错过的越多，人生真是一部遗憾的连续剧。然而，我们却在错过中得到了砥砺，学会了成长，谁能拒绝错过呢！谁说错过的就百无一用呢？没有错过，或许我们不懂得珍惜；没有错过，或许我们就让生命轻而易举地从自己手里溜过……我们不想错过，那是因为我们曾经错过。\n" +
                "　　无论怎样你都永远不会真正得到它。");
        text.setTitle("哈哈哈");
        text.setTime("2018-8-8");
        text.setTextimage("534408434.jpg");

        textDao.createText(text);
        System.out.println("添加文章成功");
    }

    @Test
    public void addComment() {
        comment comments = new comment();
        comments.setTextid(53);
        comments.setUserid("1455075085@qq.com");
        comments.setTime("2019-8-7");
        comments.setText("评论\"你好\"");
        int commentid = textDao.addCommentToText(comments);
        if (commentid != -1){
            Boolean aBoolean = textDao.addComment(comments, commentid);
            System.out.println(aBoolean);
        }else {
            System.out.println("存储评论到数据库错误，报错点（TextServiceImpl-->addComment）");
        }
    }

//    @Test
//    public void findText() {
//        text2 text = new text2();
//        text.setTextid(1);
//        TextService textService = new TextServiceImpl();
//        text1 text1 = textService.findText(text);
//        System.out.println(text1.toString());
//    }

    @Test
    public void findAlltext() {
        TextService textService = new TextServiceImpl();
        text2 alltext = textService.findAlltext(13);
        String s = JSON.toJSONString(alltext);
        System.out.println(s);
    }

    @Test
    public void deleteText() {
        textDao.deleteText(11);
    }

    @Test
    public void updateCommentNum() {
        Boolean aBoolean = textDao.updateTextCommentNum(51);
        System.out.println(aBoolean);
    }

    @Test
    public void getsimpleTextByUserID() throws JsonProcessingException {

        List<simpletext> simpletexts = textService.getsimpleTextByUserID("1455075085@qq.com");
        System.out.println(simpletexts.size());
        String s = JSON.toJSONString(simpletexts);
        System.out.println(s);

    }

    @Test
    public void getcollectionByUserID() {
        List<simpletext> simpletexts = textService.getcollectionByUserID("1455075085@qq.com");
        String s1 = JSON.toJSONString(simpletexts);
        System.out.println(s1);
    }


    @Test
    public void updateLikes() {
        textService.updateLikes(1, 13);
    }

    @Test
    public void cancelCollection() {
        collection collection = new collection();
        collection.setUserid("1455075085@qq.com");
        collection.setCollectiontextid(15);
        Boolean aBoolean = textService.cancelCollection(collection);
        System.out.println(aBoolean);
    }

    @Test
    public void getAllsimpleartcle() {
        List<simpletext_article> allsimpleartcle = textService.getAllsimpleartcle();
        String s = JSON.toJSONString(allsimpleartcle);
        System.out.println(s);
    }

    @Test
    public void getTopArticle() {
        List<simpletext_article> topArticle = textService.getTopArticle();
        String s = JSON.toJSONString(topArticle);
        System.out.println(s);
    }

    @Test
    public void searchArticleByKeyWorks() {
        List<simpletext_article> simpletext_articles = textService.searchArticleByKeyWorks("大");
        String s = JSON.toJSONString(simpletext_articles);
        System.out.println(s);
    }

    @Test
    public void addHomearticle() {
        textService.addHomearticle(51);
    }

    @Test
    public void cancleHomearticle() {
        textService.cancleHomearticle(51);
    }

    @Test
    public void getCanToHomeArtivcle() {
        List<simpletext_article> canToHomeArtivcle = textService.getCanToHomeArtivcle();
        String s = JSON.toJSONString(canToHomeArtivcle);
        System.out.println(s);
    }
}