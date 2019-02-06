package com.test.genericDependencyInject.base;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T> {

    @Autowired
    protected BaseDao<T> baseDao;

    public int insert(T record) {
        return baseDao.insert(record);
    }

    public int update(T record) {
        return baseDao.update(record);
    }

    public int delete(T record) {
        return baseDao.delete(record);
    }

    public T findById(Integer id) {
        return baseDao.findById(id);
    }

}
