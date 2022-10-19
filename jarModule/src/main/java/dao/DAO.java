package dao;

import java.sql.SQLException;

public interface DAO<T> {
    void DeleteUser(String name);
    void AddUser(T t) throws SQLException;
    void ModifyUser(T t) throws SQLException;
    T get (long id);
}
