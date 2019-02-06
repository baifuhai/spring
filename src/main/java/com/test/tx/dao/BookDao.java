package com.test.tx.dao;

import com.test.tx.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //更新库存，库存-1
    public void updateStock(int id, int stock) {
        String sql = "update book set stock = ? where id = ?";
        jdbcTemplate.update(sql, stock, id);
    }

    //获取书的单价
    public double getPrice(int id) {
        String sql = "select price from book where id = ?";
        double price = jdbcTemplate.queryForObject(sql, double.class, id);
        return price;
    }

    //获取书的库存
    public int getStock(int id) {
        String sql = "select stock from book where id = ?";
        int stock = jdbcTemplate.queryForObject(sql, int.class, id);
        return stock;
    }

    //根据id获取
    public Book getById(int id) {
        String sql = "select id, name, price, stock from book where id = ?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        Book book = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return book;
    }

}

