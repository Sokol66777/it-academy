package UserImpl;


import SQL.SQLConnection;
import dao.UserModifyDAO;
import model.Constants;
import model.User;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModifyDAOImpl implements UserModifyDAO {



    @Override
    public void AddUser(User user) throws IOException {


        File file = new File(Constants.FILE_PATH);
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(user.toString() + "\n");
        }

    }

    @Override
    public void DeleteUser(String username) throws IOException {

        try(Connection connection = SQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_DELETE_FROM_USER);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
