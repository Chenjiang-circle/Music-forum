/**
 * FileName: TextServiceImpl
 * Author:   陈江超
 * Date:     2019/7/22 11:32
 * Description: 文章操作实现
 */
package com.github.service.impl;

import com.github.dao.TextDao;
import com.github.dao.impl.TextDaoImpl;
import com.github.domain.Text;
import com.github.domain.comment;
import com.github.service.TextService;

import java.util.Map;

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

    @Override
    public Map findText(Text text) {

        return null;
    }
}
