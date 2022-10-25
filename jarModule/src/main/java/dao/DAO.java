package dao;

import java.sql.SQLException;

public interface DAO<T> {
    void Delete(String name) throws SQLException ;
    void Add(T t) throws SQLException;
    void Modify(T t) throws SQLException;
    T get (long id) throws SQLException;
}
