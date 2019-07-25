/**
 * FileName: TextDaoImpl
 * Author:   陈江超
 * Date:     2019/7/22 11:35
 * Description: TextDao实现
 */
package com.github.dao.impl;

import com.github.dao.TextDao;
import com.github.domain.Text;
import com.github.domain.comment;
import com.github.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 〈一句话功能简述〉<br>
 * 〈TextDao实现〉
 *
 * @author 陈江超
 * @create 2019/7/22
 * @since 1.0.0
 */
public class TextDaoImpl implements TextDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 把文章写入数据库中
     * @param text
     */
    @Override
    public void createText(Text text) {
        String sql = "insert into text values(?, null, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, text.getUserid(), text.getTime(), text.getLikes(), text.getComment(), text.getCollection(), text.getText(), text.getTitle(), text.getType());
    }

    /**
     * 把评论加入comment表中，并实现文章评论数加一
     * @param comments
     * @param commentid
     * @return
     */
    @Override
    public Boolean addComment(comment comments, int commentid) {
        try {
            // 将评论id和被评论文章id存到comment表中
            String sql = "insert into comment values(?, ?, ?, ?)";
            template.update(sql, comments.getTextid(), commentid, comments.getText(), comments.getTime());
            // 将被评论文章的comments属性逐层往上加一。

            // 初始化commentid。
            int comment_id = commentid;
            comment c = new comment();
            while(true){
                try {
                    // 获取commentid对应的textid，如果存在这样的textid，就将textid看做commentid继续去寻找上一级的textid并将其comment属性值加一
                    String sql3 = "select * from comment where commentid = ?";
                    c = template.queryForObject(sql3, new BeanPropertyRowMapper<comment>(comment.class), comment_id);
                    String sql2 = "update text set comment = comment + 1 where textid = ? ";
                    template.update(sql2, c.getTextid());
                    comment_id = c.getTextid();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("循环逐层加一结束");
                    c = null;
                    break;
                }
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("在插入comment表时，出现异常");
            return false;
        }
    }

    /**
     * 把评论当做文章写入text表中，生成一个唯一的textid
     * @param comments
     * @return
     */
    @Override
    public int addCommentToText(comment comments) {
        try {
            // 先将评论存入text表中，生成一个唯一的textid
            String sql = "insert into text values(?, null, ?, ?, ?, ?, ?, ?, ?)";
            template.update(sql, comments.getUserid(), comments.getTime(), comments.getLikes(), comments.getComment(), comments.getCollection(), comments.getText(), comments.getTitle(), comments.getType());
            // 获取这个唯一的textid，并返回值
            String sql2 = "select * from text where text = ? and userid = ? ";
            comment comment = template.queryForObject(sql2, new BeanPropertyRowMapper<comment>(comment.class), comments.getText(), comments.getUserid());
            return comment.getTextid();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("将评论插入文章表时出现异常,返回-1");
            return -1;
        }
    }


}
