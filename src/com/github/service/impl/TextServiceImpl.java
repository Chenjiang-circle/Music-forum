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
import com.github.service.TextService;

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
}
