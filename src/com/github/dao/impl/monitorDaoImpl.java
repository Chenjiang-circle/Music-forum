package com.github.dao.impl;

import com.github.dao.monitorDao;
import com.github.domain.webData;
import com.github.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class monitorDaoImpl implements monitorDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void view() {
        try {
            String sql = "insert into monitor values(curdate())";
            int update = template.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("浏览量加一失败!!!!!!");
        }
    }

    @Override
    public webData getWebData() {
        webData webData = new webData();
        try {
            // 1.获取网页总访问量
            String sql1 = "select count(*) from monitor";
            int count = template.queryForObject(sql1, Integer.class);
            webData.setAllPageview(count);
        } catch (Exception e){
            System.out.println("访问量为零");
            webData.setAllPageview(0);
        }
        try {
            // 2. 获取今天的访问量
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            Date date = new Date();
            String format1 = format.format(date);
            String sql2 = "select count(*) from monitor where createtime like '%" + format1 + "%'";
            int integer = template.queryForObject(sql2, Integer.class);
            webData.setTodayPageview(integer);
        } catch (Exception e) {
            System.out.println("今天访问量为零");
            webData.setTodayPageview(0);
        }
        try {
            // 3. 获取文章数
            String sql3 = "select count(*) from text where title is not null";
            int integer = template.queryForObject(sql3, Integer.class);
            webData.setAllArticle(integer);
        } catch (Exception e) {
            System.out.println("没有文章");
            webData.setAllArticle(0);
        }
        try {
            // 4. 获取昨天发表的文章数
            // 4.1获取昨天的日期
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,-1);
            String yesterday = new SimpleDateFormat( "MM-dd ").format(cal.getTime());
            String sql4 = "select count(*) from text where time like '%" + yesterday + "%'";
            int integer = template.queryForObject(sql4, Integer.class);
            webData.setYestArticle(integer);
        } catch (Exception e){
            System.out.println("昨天没有人发表文章");
            webData.setYestArticle(0);
        }
        try {
            // 5. 获取乐评数
            String sql5 = "select count(*) from text where type like '%乐评%'";
            int integer = template.queryForObject(sql5, Integer.class);
            webData.setMusicReviews(integer);
        } catch (Exception e) {
            System.out.println("没有关于乐评的文章");
            webData.setMusicReviews(0);
        }
        try {
            // 6. 获取音乐故事数
            String sql6 = "select count(*) from text where type like '%音乐故事%'";
            int integer = template.queryForObject(sql6, Integer.class);
            webData.setMusicStory(integer);
        } catch (Exception e) {
            System.out.println("没有关于音乐故事的文章");
            webData.setMusicStory(0);
        }
        try {
            // 7. 华语数
            String sql7 = "select count(*) from text where type like '%华语%'";
            int integer = template.queryForObject(sql7, Integer.class);
            webData.setMandarin(integer);
        } catch (Exception e) {
            System.out.println("没有关于华语的文章");
            webData.setMandarin(0);
        }
        try {
            // 8. 欧美数
            String sql8 = "select count(*) from text where type like '%欧美%'";
            int integer = template.queryForObject(sql8, Integer.class);
            webData.setWestern(integer);
        } catch (Exception e) {
            System.out.println("没有关于欧美的文章");
            webData.setWestern(0);
        }
        try {
            // 9. 摇滚数
            String sql9 = "select count(*) from text where type like '%摇滚%'";
            int integer = template.queryForObject(sql9, Integer.class);
            webData.setRock(integer);
        } catch (Exception e) {
            System.out.println("没有关于摇滚的文章");
            webData.setRock(0);
        }
        try {
            // 10. 流行数
            String sql10 = "select count(*) from text where type like '%流行%'";
            int integer = template.queryForObject(sql10, Integer.class);
            webData.setPop(integer);
        } catch (Exception e) {
            System.out.println("没有关于流行的文章");
            webData.setPop(0);
        }
        try {
            // 11. 嘻哈数
            String sql11 = "select count(*) from text where type like '%嘻哈%'";
            int integer = template.queryForObject(sql11, Integer.class);
            webData.setRap(integer);
        } catch (Exception e) {
            System.out.println("没有关于嘻哈的文章");
            webData.setRap(0);
        }
        return webData;
    }
}
