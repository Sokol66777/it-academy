package dao;

import model.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserModifyDAO {
    void DeleteUser(String username);
    public void AddUser(String username,String password, String email) throws SQLException;
    public void ModifyUser(String newUsername, String newEmail, String oldUsername) throws SQLException;

    public void ModifyUser(String newUsername, String newPassword, String newEmail, String oldUsername) throws SQLException;

}
