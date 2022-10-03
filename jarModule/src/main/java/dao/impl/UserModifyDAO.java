package dao.impl;

import java.io.IOException;

public interface UserModifyDAO {
    void AddUser() throws IOException;
    void DeleteUser(String username)throws IOException;
    void ModifyUser(String username)throws IOException;
}
