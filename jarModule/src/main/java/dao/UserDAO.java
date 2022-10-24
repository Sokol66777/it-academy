package dao;


import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers() throws SQLException;
    User getByUsername(String username) throws  SQLException;
    User getByEmail(String email) ;

}
