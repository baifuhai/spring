package com.test.genericDependencyInject.dao;

import com.test.genericDependencyInject.base.BaseDao;
import com.test.genericDependencyInject.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements BaseDao<User> {

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int update(User record) {
        return 0;
    }

    @Override
    public int delete(User record) {
        return 0;
    }

    @Override
    public User findById(Integer id) {
        return new User(id);
    }

}
