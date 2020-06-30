package cn.hoj.travel.dao.impl;

import cn.hoj.travel.dao.CategoryDao;
import cn.hoj.travel.domain.Category;
import cn.hoj.travel.util.JDBCUtils;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryImpl implements CategoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql="select * from  tab_category";
        List<Category> query = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return query;
    }
}
