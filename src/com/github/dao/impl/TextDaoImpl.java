/**
 * FileName: TextDaoImpl
 * Author:   陈江超
 * Date:     2019/7/22 11:35
 * Description: TextDao实现
 */
package com.github.dao.impl;

import com.github.dao.TextDao;
import com.github.domain.*;
import com.github.util.JDBCUtils;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String sql = "insert into text values(?, null, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, text.getUserid(), text.getTime(), text.getLikes(), text.getComment(), text.getCollection(), text.getText(), text.getTitle(), text.getType(), text.getTextimage());
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
            String sql = "insert into text values(?, null, ?, ?, ?, ?, ?, ?, ?, ?)";
            template.update(sql, comments.getUserid(), comments.getTime(), comments.getLikes(), comments.getComment(), comments.getCollection(), comments.getText(), comments.getTitle(), comments.getType(), comments.getTextimage());
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

//    /**
//     * 根据文章id查询文章具体信息
//     * @param text
//     * @return
//     */
//    @Override
//    public Text findText(text2 text) {
//        try {
//            String sql = "select * from text where textid = ? ";
//            Text text1 = template.queryForObject(sql, new BeanPropertyRowMapper<Text>(Text.class), text.getTextid());
//            return text1;
//        }catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public text2 findFirstComment(int textid) {
        try {
            // 根据文章id找到对应的文章信息
            String sql = "select * from text where textid = ?";
            text2 text = template.queryForObject(sql, new BeanPropertyRowMapper<text2>(text2.class), textid);
            // 根据文章id找到对应的作者的昵称
            String sql1 = "select * from user where userid = ?";
            Text text1 = template.queryForObject(sql1, new BeanPropertyRowMapper<Text>(Text.class), text.getUserid());
            text.setUsername(text1.getUsername());
            List<comment1> findtextid = null;
            try {
                String sql2 = "select * from comment where textid = " + textid;
                findtextid = template.query(sql2, new BeanPropertyRowMapper<comment1>(comment1.class));
                for (comment1 textttt:findtextid) {
                    System.out.println(textttt.getCommentid());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("textid=" + textid +"的文章没有子评论!");
                findtextid = null;
            }
            if (findtextid != null){
                try {
                    ArrayList<text2> text2s = new ArrayList<text2>();
                    for (comment1 atextid: findtextid) {
                        text2 firstComment = findFirstComment(atextid.getCommentid());
                        text2s.add(firstComment);
                    }
                    text.setList(text2s);
                }catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("空指针异常！");
                }

            }
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("找不到textid=" + textid + "，的文章！");
            return null;
        }

    }

    @Override
    public Boolean deleteText(int textid) {
        try {
            List<comment1> comment1s = null;
            try {
                String sql0 = "select * from comment where textid = " + textid;
                comment1s = template.query(sql0, new BeanPropertyRowMapper<comment1>(comment1.class));
                for (comment1 c: comment1s) {
                    deleteText(c.getCommentid());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("textid = " + textid + ", 没有子评论");
            }

            String sql = "delete from text where textid = ? ";
            template.update(sql, textid);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除文章失败" + textid);
            return false;
        }

    }

    @Override
    public Boolean updateTextCommentNum(int textid) {
        try {
            List<comment1> comment1s = null;
            try {
                String sql = "select * from comment where textid = " + textid;
                comment1s = template.query(sql, new BeanPropertyRowMapper<comment1>(comment1.class));
                for (comment1 c: comment1s) {
                    updateTextCommentNum(c.getCommentid());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("该文章没有子评论 " + textid);
            }
            int num = comment1s.size();
            String sql1 = "update text set comment=? where textid = ?";
            template.update(sql1, num, textid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("该文章不存在 " + textid);
            return false;
        }
    }

    @Override
    public List<simpletext> getsimpleTextByUserID(String userid) {
        try {
            String sql = "select text.textid, text.title, text.textimage from text where text.userid='" + userid +"' and title is not null";
            List<simpletext> query = template.query(sql, new BeanPropertyRowMapper<simpletext>(simpletext.class));
            return query;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("该用户没有写文章!");
            return null;
        }
    }

    @Override
    public List<simpletext> getcollectionByUserID(String userid) {
        try {
            String sql = "select text.textid, text.title, text.textimage from text, collection where text.textid = collection.collectiontextid and collection.userid = '" + userid +"'";
            List<simpletext> query = template.query(sql, new BeanPropertyRowMapper<simpletext>(simpletext.class));
            return query;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("该用户没有收藏文章");
            return null;
        }
    }

    @Override
    public void updateLikes(int likes, int textid) {
        try {
            String sql = "update text set likes = likes + 1 where textid = ?";
            int update = template.update(sql, textid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("点赞加一失败,可能是文章id不存在.");
        }

    }

    @Override
    public Boolean cancelCollection(collection collection) {
        try {
            String sql = "delete from collection where userid = ? and collectiontextid = ?";
            int update = template.update(sql, collection.getUserid(), collection.getCollectiontextid());
            String sql1 = "update text set collection = collection - 1 where textid = ?";
            int update1 = template.update(sql1, collection.getCollectiontextid());
            if (update == 1 && update1 == 1){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("取消关注的文章不存在,或者其他愿意..");
            return false;
        }
    }

    @Override
    public List<simpletext_article> getAllsimpleartcle() {
        try {
            String sql = "select * from text where title is not null";
            List<simpletext_article> query = template.query(sql, new BeanPropertyRowMapper<simpletext_article>(simpletext_article.class));
            return query;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("获取所有文章失败");
            return null;
        }
    }

    @Override
    public List<simpletext_article> getTopArticle() {
        try {
            String sql = "select * from text where title is not null order by likes desc";
            List<simpletext_article> query = template.query(sql, new BeanPropertyRowMapper<simpletext_article>(simpletext_article.class));
            return query;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询最多赞的文章失败");
            return null;
        }
    }

    @Override
    public List<simpletext_article> searchArticleByKeyWorks(String keyworks) {
        try {
            String sql = "select * from text where title <> 'NULL' and (title like '%" + keyworks + "%' or text like '%" + keyworks + "%') order by likes desc";
            List<simpletext_article> query = template.query(sql, new BeanPropertyRowMapper<simpletext_article>(simpletext_article.class));
            return query;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("搜索不到与该关键字有关的文章");
            return null;
        }
    }

    @Override
    public void addHomearticle(int textid) {
        try {
            String sql = "insert into hometext values(?, 'pass')";
            int update = template.update(sql, textid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("可能此文章已经在home页展示了,不需要再添加上去");
        }
    }

    @Override
    public void cancleHomearticle(int textid) {
        try {
            String sql = "delete from hometext where textid = ?";
            int update = template.update(sql, textid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("可能取消的这篇文章本来就不在home页展示");
        }
    }

    @Override
    public List<simpletext_article> getCanToHomeArtivcle() {
        try {
            String sql = "select text.textid, text.title, text.textimage, text.likes, text.comment, text.type from text, hometext where text.textid = hometext.textid and hometext.ispass = 'pass'";
            List<simpletext_article> query = template.query(sql, new BeanPropertyRowMapper<simpletext_article>(simpletext_article.class));
            return query;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("没有获取到能上传到home页的文章");
        }

        return null;
    }


//    @Override
//    public ArrayList<Text> findFirstComment(int textid) {
//        try {
//            String sql = "select * from text where textid in (select commentid from comment where textid = "+ textid +"))";
//            List<Text> list = template.query(sql, new BeanPropertyRowMapper<Text>(Text.class));
//            return (ArrayList<Text>) list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


}

