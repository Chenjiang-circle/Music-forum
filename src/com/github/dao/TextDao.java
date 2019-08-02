package com.github.dao;

import com.github.domain.Text;
import com.github.domain.comment;
import com.github.domain.simpletext;
import com.github.domain.text2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TextDao {
    void createText(Text text);

    /**
     * 将评论写入评论（comment）表中
     * @param comments
     * @param commentid
     * @return
     */
    Boolean addComment(comment comments, int commentid);

    /**
     * 将评论存入文章中，返回它的textid值
     * @param comments
     * @return
     */
    int addCommentToText(comment comments);

//    /**
//     * 根据文章id获取文章具体信息
//     * @param text
//     * @return
//     */
//    Text findText(text2 text);

    /**
     * 此方法用来找到文章以及其所有评论
     * @param textid
     * @return
     */
    text2 findFirstComment(int textid);

    /**
     * 查询数据库，将对应的文章和评论删除
     * @param textid
     * @return
     */
    Boolean deleteText(int textid);

    /**
     *  更新文章评论数
     * @param textid
     * @return
     */
    Boolean updateTextCommentNum(int textid);

    /**
     * 通过userid查询用户的所有文章封面和标题
     * @param userid
     * @return
     */
    List<simpletext> getsimpleTextByUserID(String userid);

    /**
     * 通过userid查询用户的所有收藏的文章的封面和标题,以及对应的文章id
     * @param userid
     * @return
     */
    List<simpletext> getcollectionByUserID(String userid);
}
