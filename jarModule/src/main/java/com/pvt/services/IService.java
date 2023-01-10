package com.pvt.services;

import com.pvt.exceptions.LogicException;

public interface IService <T,ID>{

    void add(T t) throws LogicException;

    void delete(ID id);

    void modify(T t) throws LogicException;

    T get(ID id);
}
