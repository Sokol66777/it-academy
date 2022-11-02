package dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface DAO<T> {
    void delete(long id) ;
    void add(T t) ;
    void modify(T t) throws SQLException, PropertyVetoException;
    T get (long id) ;
}
