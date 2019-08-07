package com.github.dao;

import com.github.domain.*;

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

    /**
     * 此方法用来更新文章喜欢数
     * @param likes
     */
    void updateLikes(int likes, int textid);

    /**
     * 该方法用来取消对谋篇文章的收藏
     * @param collection
     * @return
     */
    Boolean cancelCollection(collection collection);

    /**
     * 该方法用来获取所有文章的详情(不包括内容),用于在乐评页进行展示
     * @return
     */
    List<simpletext_article> getAllsimpleartcle();

    /**
     * 获取热度高的文章
     * @return
     */
    List<simpletext_article> getTopArticle();

    /**
     * 通过关键字查询文章
     * @param keyworks
     * @return
     */
    List<simpletext_article> searchArticleByKeyWorks(String keyworks);

    /**
     * 管理员让某一篇文章上传到home主页
     * @param textid
     */
    void addHomearticle(int textid);

    /**
     * 管理员取消某篇文章再首页展示
     * @param textid
     */
    void cancleHomearticle(int textid);

    /**
     * 获取所有管理员同意上传到首页的文章
     * @return
     */
    List<simpletext> getCanToHomeArtivcle();
}
