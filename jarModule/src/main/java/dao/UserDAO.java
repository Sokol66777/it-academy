package dao;

import model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() ;
    User getByUsername(String username);

    User getByID(long ID);

    User getByEmail(String email);

}
