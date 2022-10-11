package dao;

import model.User;

import java.io.IOException;

public interface UserModifyDAO {
    void AddUser(User user) throws IOException;
    void DeleteUser(String username)throws IOException;
    void ModifyUser(String username)throws IOException;
}
