package com.pvt.dao;

import com.pvt.exceptions.LogicException;


public interface DAO<T> {
    void delete(long id) ;
    void add(T t) throws LogicException;
    void modify(T t) throws LogicException;
    T get (long id) ;
}
