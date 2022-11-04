package dao;


import model.User;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers() ;
    User getByUsername(String username);
    User getByEmail(String email) ;

}
