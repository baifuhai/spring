package com.test.genericDependencyInject.base;

public interface BaseDao<T> {

    int insert(T record);

    int update(T record);

    int delete(T record);

    T findById(Integer id);

}
