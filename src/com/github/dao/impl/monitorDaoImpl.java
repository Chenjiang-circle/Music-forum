package com.github.dao.impl;

import com.github.dao.monitorDao;
import com.github.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
