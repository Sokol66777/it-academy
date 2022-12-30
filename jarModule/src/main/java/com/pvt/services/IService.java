package com.pvt.services;

import com.pvt.exceptions.LogicException;

public interface IService <T>{

    void add(T t) throws LogicException;

    void delete(long id);

    void modify(T t) throws LogicException;

    T get(long id);
}
