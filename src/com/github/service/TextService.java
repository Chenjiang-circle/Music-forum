package com.github.service;

import com.github.domain.Text;
import com.github.domain.comment;

import java.util.Map;

public interface TextService {
    /**
     * 发表文章
     * @param text
     */
    void createText(Text text);

    /**
     * 添加评论
     * @param comments
     * @return
     */
    Boolean addComment(comment comments);

    /**
     * 查找文章对应的内容，以及评论内容
     * @param text
     * @return
     */
    Map findText(Text text);
}
