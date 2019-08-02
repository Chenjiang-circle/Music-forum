/**
 * FileName: TextServiceImpl
 * Author:   陈江超
 * Date:     2019/7/22 11:32
 * Description: 文章操作实现
 */
package com.github.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.dao.TextDao;
import com.github.dao.UserDao;
import com.github.dao.impl.TextDaoImpl;
import com.github.dao.impl.UserDaoImpl;
import com.github.domain.*;
import com.github.service.TextService;

import java.util.ArrayList;
import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 * 〈文章操作实现〉
 *
 * @author 陈江超
 * @create 2019/7/22
 * @since 1.0.0
 */
public class TextServiceImpl implements TextService {
    TextDao textDao = new TextDaoImpl();

    @Override
    public void createText(Text text) {
        textDao.createText(text);
    }

    @Override
    public Boolean addComment(comment comments) {
        int commentid = textDao.addCommentToText(comments);
        if (commentid != -1){
            return textDao.addComment(comments, commentid);
        }else {
            System.out.println("存储评论到数据库错误，报错点（TextServiceImpl-->addComment）");
            return false;
        }

    }

//    @Override
//    public text1 findText(text2 text) {
//
//        Text textDaoText = textDao.findText(text);
//        if (textDaoText != null){
//            // 将textDaoText对象转换为json字符串
//            String s = JSON.toJSONString(textDaoText);
//            // 将Text对象的参数拷贝到text1对象中
//            text1 textObject = JSON.parseObject(s, text1.class);
//            // 根据要查找的文章id，找到对应为作者的昵称
//            UserDao userDao = new UserDaoImpl();
//            User user = userDao.findUserByTextId(text.getTextid());
//            // 在textObject中设置username参数的值
//            textObject.setUsername(user.getUsername());
//            return textObject;
//        }
//        else{
//            return null;
//        }
//
//    }

    @Override
    public text2 findAlltext(int textid) {
        return textDao.findFirstComment(textid);
    }

    @Override
    public Boolean deleteText(int textid) {
        return textDao.deleteText(textid);
    }

    @Override
    public Boolean updateCommentNum(int textid) {
        return textDao.updateTextCommentNum(textid);
    }

    @Override
    public List<simpletext> getsimpleTextByUserID(String userid) {
        System.out.println("getsimpleTextByUserID被调用");
        return textDao.getsimpleTextByUserID(userid);
    }

    @Override
    public List<simpletext> getcollectionByUserID(String userid) {
        return textDao.getcollectionByUserID(userid);
    }


//    @Override
//    public text2 findAlltext(ArrayList<Text> text) {
//        ArrayList<text2> list2 = null;
//        for (Text atext : text) {
//            TextService textService = new TextServiceImpl();
//            text1 text1 = textService.findText(atext);
//            String s = JSON.toJSONString(text1);
//            text2 textObject = JSON.parseObject(s, text2.class);
//            TextDao textDao = new TextDaoImpl();
//            ArrayList<Text> firstComment = textDao.findFirstComment(textObject.getTextid());
//            if (firstComment != null) {
//                text2 alltext = findAlltext(firstComment);
//                list2.add(alltext);
//            } else {
//                list2.add(textObject);
//            }
//            textObject.setList(list2);
//        }
//        return text.get(0);
//    }
}
