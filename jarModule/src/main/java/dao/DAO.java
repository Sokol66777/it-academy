package dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public interface DAO<T> {
    void Delete(String name) throws SQLException, PropertyVetoException;
    void Add(T t) throws SQLException, PropertyVetoException;
    void Modify(T t) throws SQLException, PropertyVetoException;
    T get (long id) throws SQLException, PropertyVetoException;
}
