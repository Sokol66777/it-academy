package dao.impl;

import model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws IOException;
    User getByUsername(String username) throws IOException;

    User getByID(long ID) throws IOException;

    User getByEmail(String email) throws IOException;
}
