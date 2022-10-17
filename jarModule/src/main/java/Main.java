import UserImpl.UserDAOImpl;
import UserImpl.UserModifyDAOImpl;
import dao.UserDAO;
import dao.UserModifyDAO;
import model.Constants;
import model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        UserDAO userDAO = new UserDAOImpl();
        UserModifyDAO userModifyDAO = new UserModifyDAOImpl();
        List<User> users = new ArrayList<>();
        users = userDAO.getAllUsers();
        for(User user : users){
            System.out.println(user.toString());
        }
        userModifyDAO.DeleteUser("user6");
        List<User> newUsers = new ArrayList<>();
        newUsers=userDAO.getAllUsers();
        for(User user : newUsers){
            System.out.println(user.toString());
        }

    }
}
