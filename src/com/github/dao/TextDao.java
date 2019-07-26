package com.github.dao;

import com.github.domain.Text;
import com.github.domain.comment;

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

    Map findText(Text text);
}
