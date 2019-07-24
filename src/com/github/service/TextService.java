package com.github.service;

import com.github.domain.Text;
import com.github.domain.comment;

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
}
