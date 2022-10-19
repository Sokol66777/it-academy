package dao;

import model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers() ;
    User getByUsername(String username);
    User getByEmail(String email);

}
