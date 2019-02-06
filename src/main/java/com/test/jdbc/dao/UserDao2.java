package com.test.jdbc.dao;

import com.test.jdbc.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * 不推荐使用 JdbcDaoSupport，而推荐直接使用 JdbcTemplate 作为 Dao 类的成员变量
 */
@Repository
public class UserDao2 extends JdbcDaoSupport {

    @Autowired
    public void setDataSource2(DataSource dataSource) {
        //setDataSource(dataSource);
    }

    @Autowired
    public void setJdbcTemplate2(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    public User getById(Integer id) {
        String sql = "select id, name, age from user where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return getJdbcTemplate().queryForObject(sql, rowMapper, id);
    }

}
