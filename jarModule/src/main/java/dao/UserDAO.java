package dao;


import model.User;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers() throws SQLException, PropertyVetoException;
    User getByUsername(String username) throws SQLException, PropertyVetoException;
    User getByEmail(String email) throws PropertyVetoException, SQLException;

}
