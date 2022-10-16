import UserImpl.UserDAOImpl;
import dao.UserDAO;
import model.Constants;
import model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        UserDAO userDAO = new UserDAOImpl();
        long id = userDAO.getGreatestID();
        System.out.println(id);

    }
}
