package com.github.service;

import com.github.domain.Text;
import com.github.domain.comment;
import com.github.domain.text1;
import com.github.domain.text2;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
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
     * 查找文章对应的内容(包括文章作者的昵称)
     * @param text
     * @return text1 返回text1类型对象，此对象不仅包括Text对象中的所有参数，还有文章对应的作者昵称“username”参数
     */
    text1 findText(text2 text);

    /**
     * 此方法用来找到所有的文章加评论
     * @param textid
     * @return
     */
    text2 findAlltext(int textid);

    /**
     * 传入文章id，将文章和评论一同删除
     * @param textid
     * @return
     */
    Boolean deleteText(int textid);

    /**
     * 当删除评论时，需要重新更新文章评论数，使用此方法可以更新文章评论数，更新成功返回true.
     * @param textid
     * @return
     */
    Boolean updateCommentNum(int textid);
}
