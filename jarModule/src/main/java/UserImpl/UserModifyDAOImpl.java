package UserImpl;


import SQL.SQLConnection;
import dao.UserModifyDAO;
import model.Constants;

import java.sql.*;


public class UserModifyDAOImpl implements UserModifyDAO {

    public void AddUser(String username,String password, String email) throws SQLException {

        try(Connection connection = SQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_ADD_INTO_USER);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.executeUpdate();
        }


    }

    @Override
    public void ModifyUser(String newUsername, String newEmail, String oldUsername) throws SQLException {
        try(Connection connection = SQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_UPDATE_USER_WHITHOUT_PASSWORD);
            preparedStatement.setString(1,newUsername);
            preparedStatement.setString(2,newEmail);
            preparedStatement.setString(3,oldUsername);
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public void ModifyUser(String newUsername, String newPassword, String newEmail, String oldUsername) throws SQLException {
        try(Connection connection = SQLConnection.getConnection()) {
           PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_UPDATE_USER_WHITH_PASSWORD);
           preparedStatement.setString(1,newUsername);
            preparedStatement.setString(2,newPassword);
            preparedStatement.setString(3,newEmail);
            preparedStatement.setString(4,oldUsername);
           preparedStatement.executeUpdate();

        }
    }


    @Override
    public void DeleteUser(String username) {

        try(Connection connection = SQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_DELETE_FROM_USER);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
