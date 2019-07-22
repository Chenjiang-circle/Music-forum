/**
 * FileName: UserDaoImpl
 * Author:   陈江超
 * Date:     2019/7/21 9:21
 * Description: 操作数据库
 */
package com.github.dao.impl;

import com.github.dao.UserDao;
import com.github.domain.User;
import com.github.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
        String sql = "insert into user values(?, null, ?, 1, 0, 0, 0)";
        template.update(sql, users.getUserid(), users.getPassword());
    }

    @Override
    public User signin(User users) {
        try {
            String sql = "select * from user where userid = ? and password = ? ";
            User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), users.getUserid(), users.getPassword());
            return user1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
