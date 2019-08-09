/**
 * FileName: UserDaoImpl
 * Author:   陈江超
 * Date:     2019/7/21 9:21
 * Description: 操作数据库
 */
package com.github.dao.impl;

import com.github.dao.UserDao;
import com.github.domain.Text;
import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.follow;
import com.github.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈操作数据库〉
 *
 * @author 陈江超
 * @create 2019/7/21
 * @since 1.0.0
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void register(User users) {
        String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, users.getUserid(), users.getUsername(), users.getPassword(), users.getNumsignin(), users.getFans(), users.getDescription(), users.getSex(), users.getImageid());
    }

    /**
     * 登录操作
     * @param users
     * @return
     */
    @Override
    public User signin(User users) {
        try {
            String sql = "select * from user where userid = '"+users.getUserid()+"' and password = ? ";
            User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), users.getPassword());
            return user1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验用户是否登录
     * @param users
     * @return
     */
    @Override
    public Boolean isHave(User users) {
        try {
            String sql = "select * from user where userid = ? ";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), users.getUserid());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void follow(follow followers) {
        String sql = "insert into follow values (?, ?)";
        template.update(sql, followers.getUserid(), followers.getFollowed());
        String sql1 = "update user set fans = fans + 1 where userid = ?";
        int update = template.update(sql1, followers.getFollowed());
    }

    @Override
    public Boolean isFollow(follow follows) {
        try {
            String sql = "select * from follow where userid = ? and followed = ? ";
            follow follow = template.queryForObject(sql, new BeanPropertyRowMapper<follow>(follow.class), follows.getUserid(), follows.getFollowed());
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findUserByTextId(int textid) {
        try {
            String sql = "select * from text where textid = ? ";
            Text text = template.queryForObject(sql, new BeanPropertyRowMapper<Text>(Text.class), textid);
            String sql1 = "select * from user where userid = ? ";
            User user = template.queryForObject(sql1, new BeanPropertyRowMapper<User>(User.class), text.getUserid());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean addCollectionText(collection collection) {
        try {
            String sql = "insert into collection values(?, ?)";
            int update = template.update(sql, collection.getUserid(), collection.getCollectiontextid());
            String sql1 = "update text set collection = collection + 1 where textid = ?";
            int update1 = template.update(sql1, collection.getCollectiontextid());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean isCollection(collection collections) {
        try {
            String sql = "select * from collection where userid = ? and collectiontextid = ? ";
            collection collection = template.queryForObject(sql, new BeanPropertyRowMapper<collection>(collection.class), collections.getUserid(), collections.getCollectiontextid());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean addCheckin(String userid) {
        String sql = "update user set numsignin = numsignin + 1 where userid = ?";
        int update = template.update(sql, userid);
        if (update == 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean cancelFollow(follow follow) {
        try {
            String sql = "delete from follow where userid='"+follow.getUserid()+"' and followed='" + follow.getFollowed() + "'";
            int update = template.update(sql);
            String sql1 = "update user set fans = fans - 1 where userid = ?";
            template.update(sql1, follow.getFollowed());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除关注失败,可能是用户不存在");
            return false;
        }
    }

    @Override
    public User getUserByUserID(String userid) {
        try {
            String sql = "select * from user where userid ='" + userid +"'";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class));
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("通过用户id查询用户信息失败!可能是因为用户id不存在.");
            return null;
        }
    }

    @Override
    public List<User> getAllFollowedUser(String userid) {
        try {
            String sql = "select user.userid, user.username, user.numsignin, user.fans, user.description, user.sex, user.imageid from user,follow where follow.userid = '" + userid +"' and user.userid = follow.followed";
            List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
            return users;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("这个用户没有follow别人");
            return null;
        }
    }

    @Override
    public int countFollowedNumByUserId(String userid) {
        try {
            String sql = "select * from follow where userid = '" + userid+ "'";
            List<follow> query = template.query(sql, new BeanPropertyRowMapper<follow>(follow.class));
            return query.size();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("语法错误,请更正");
            return 0;
        }
    }

    @Override
    public Boolean changeUserimage(String url, String userid) {
        try {
            String sql = "update user set imageid = ? where userid = ?";
            int update = template.update(sql, url, userid);
            if (update == 1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("更换头像异常！！！！");
            return false;
        }
    }
}
