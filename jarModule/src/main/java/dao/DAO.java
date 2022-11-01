package dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public interface DAO<T> {
    void delete(String name) throws SQLException, PropertyVetoException;
    void add(T t) throws SQLException, PropertyVetoException;
    void modify(T t) throws SQLException, PropertyVetoException;
    T get (long id) throws SQLException, PropertyVetoException;
}
