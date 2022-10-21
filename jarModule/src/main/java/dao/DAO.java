package dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public interface DAO<T> {
    void DeleteUser(String name) throws SQLException ;
    void AddUser(T t) throws SQLException;
    void ModifyUser(T t) throws SQLException;
    T get (long id) throws SQLException;
}
