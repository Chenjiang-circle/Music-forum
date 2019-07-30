package com.github.dao;

import com.github.domain.Text;
import com.github.domain.comment;
import com.github.domain.text2;

import java.util.ArrayList;
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

    /**
     * 根据文章id获取文章具体信息
     * @param text
     * @return
     */
    Text findText(text2 text);

    /**
     * 此方法用来找到文章以及其所有评论
     * @param textid
     * @return
     */
    text2 findFirstComment(int textid);
}
