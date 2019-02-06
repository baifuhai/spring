package com.test.tx.dao;

import com.test.tx.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //更新余额
    public void updateBalance(int id, double balance) {
        String sql = "update account set balance = ? where id = ?";
        jdbcTemplate.update(sql, balance, id);
    }

    //根据id获取
    public Account getById(int id) {
        String sql = "select id, balance from account where id = ?";
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
        Account account = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return account;
    }

}