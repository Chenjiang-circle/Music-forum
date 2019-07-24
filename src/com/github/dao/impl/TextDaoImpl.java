/**
 * FileName: TextDaoImpl
 * Author:   陈江超
 * Date:     2019/7/22 11:35
 * Description: TextDao实现
 */
package com.github.dao.impl;

import com.github.dao.TextDao;
import com.github.domain.Text;
import com.github.util.JDBCUtils;
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
    @Override
    public void createText(Text text) {
        String sql = "insert into text values(?, null, ?, ?, ?, ?, ?)";
        template.update(sql, text.getUserid(), text.getTime(), text.getLikes(), text.getComment(), text.getCollection(), text.getText());
    }
}
