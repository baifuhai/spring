package com.test;

import com.test.jdbc.bean.User;
import com.test.jdbc.dao.UserDao;
import com.test.jdbc.dao.UserDao2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsJdbc {

    private ConfigurableApplicationContext ctx;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("beans-jdbc.xml");
        jdbcTemplate = ctx.getBean(JdbcTemplate.class);
        namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
    }

    @After
    public void after() {
        ctx.close();
    }

    /**
     * 执行 insert, update, delete
     */
    @Test
    public void testUpdate() {
        String sql = "insert into user (name, age) values (?, ?)";
        jdbcTemplate.update(sql, "a", 1);
    }

    /**
     * 批量执行 insert, update, delete
     * 最后一个参数是 Object[] 的 List 类型，因为修改一条记录需要一个 Object 的数组，那么多条就需要多个 Object[]
     */
    @Test
    public void testBatchUpdate(){
        String sql = "insert into user (name, age) values (?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"aa", 11});
        batchArgs.add(new Object[]{"bb", 22});

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Test
    public void testQueryForMap() {
        String sql = "select * from user where id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1);
        System.out.println(map);
    }

    @Test
    public void testQueryForMapList() {
        String sql = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(list);
    }

    /**
     * 从数据库中获取一条记录, 实际得到对应的一个对象
     * 注意不是调用 queryForObject(String sql, Class<T> requiredType, Object... args)
     * 而是调用 queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
     * 1. 其中的 RowMapper 指定如何去映射结果集的行，常用的实现类为 BeanPropertyRowMapper
     * 2. 使用 SQL 中列的别名完成列名和类的属性名的映射，例如 last_name lastName
     * 3. 不支持级联属性，JdbcTemplate 到底是一个 JDBC 的小工具，而不是 ORM 框架
     */
    @Test
    public void testQueryForEntity() {
        String sql = "select * from user where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, 1);
        System.out.println(user);
    }

    /**
     * 查询实体类的集合
     * 注意调用的不是 queryForList 方法
     */
    @Test
    public void testQueryForEntityList() {
        String sql = "select * from user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> list = jdbcTemplate.query(sql, rowMapper);
        System.out.println(list);
    }

    /**
     * 获取单个列的值，或做统计查询
     * 使用 queryForObject(String sql, Class<T> requiredType)
     */
    @Test
    public void testQueryForObject() {
        String sql = "select name from user where id = ?";
        String name = jdbcTemplate.queryForObject(sql, String.class, 1);
        System.out.println(name);
    }

    @Test
    public void testQueryForObjectList() {
        String sql = "select name from user";
        List<String> list = jdbcTemplate.queryForList(sql, String.class);
        System.out.println(list);
    }

    @Test
    public void testUserDao(){
        UserDao userDao = ctx.getBean(UserDao.class);
        System.out.println(userDao.getById(1));
    }

    @Test
    public void testUserDao2(){
        UserDao2 userDao2 = ctx.getBean(UserDao2.class);
        System.out.println(userDao2.getById(1));
    }

    /**
     * 可以为参数起名字
     * 1. 好处: 若有多个参数，则不用再去对应位置，直接对应参数名，便于维护
     * 2. 缺点: 较为麻烦
     */
    @Test
    public void testNamedParameterJdbcTemplate(){
        String sql = "insert into user (name, age) values (:name, :age)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "cc");
        paramMap.put("age", 33);

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    /**
     * 使用具名参数时，可以使用 update(String sql, SqlParameterSource paramSource) 方法进行更新操作
     * 1. SQL 语句中的参数名和类的属性一致
     * 2. 使用 SqlParameterSource 的 BeanPropertySqlParameterSource 实现类作为参数
     */
    @Test
    public void testNamedParameterJdbcTemplate2(){
        String sql = "insert into user (name, age) values (:name, :age)";

        User user = new User();
        user.setName("dd");
        user.setAge(44);

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);

        namedParameterJdbcTemplate.update(sql, paramSource);
    }

}
