package dao;

import exceptions.ConnectionToDataBaseException;
import model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers() throws SQLException;
    User getByUsername(String username) throws  SQLException;
    User getByEmail(String email) ;

}
