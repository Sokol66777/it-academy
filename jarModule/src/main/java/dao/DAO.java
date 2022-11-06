package dao;

import exceptions.LogicException;
import exceptions.UserLogicException;
import model.User;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface DAO<T> {
    void delete(long id) ;
    void add(T t) throws UserLogicException;
    void modify(T t) throws UserLogicException;
    T get (long id) ;
}
