package UserImpl;


import SQL.SQLConnection;
import dao.UserDAO;
import model.Constants;
import model.User;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        try(Connection connection = SQLConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_GET_ALL_USERS);
            while (resultSet.next()){
                User user = new User();
                long id = resultSet.getLong("ID");
                String username = resultSet.getString("name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                user.setID(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(role);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return users;
    }


    @Override
    public User getByUsername(String username) throws IOException {
        User user=null;

        try (Connection connection = SQLConnection.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_GET_BY_NAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user=new User();
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                user.setEmail(email);
                user.setUsername(name);
                user.setPassword(password);
                user.setID(id);
                user.setRole(role);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public User getByID(long ID) throws IOException {
        User user = null;
        try(Connection connection = SQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_GET_BY_ID);
            preparedStatement.setLong(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String usersEmail = resultSet.getString("email");
                String role = resultSet.getString("role");
                user.setEmail(usersEmail);
                user.setUsername(name);
                user.setPassword(password);
                user.setID(id);
                user.setRole(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws IOException {
       User user = null;
       try(Connection connection = SQLConnection.getConnection()) {
           PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_GET_BY_EMAIL);
           preparedStatement.setString(1,email);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               user = new User();
               long id = resultSet.getLong("ID");
               String name = resultSet.getString("name");
               String password = resultSet.getString("password");
               String usersEmail = resultSet.getString("email");
               String role = resultSet.getString("role");
               user.setEmail(usersEmail);
               user.setUsername(name);
               user.setPassword(password);
               user.setID(id);
               user.setRole(role);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return user;
    }

    @Override
    public long getGreatestID() throws IOException {
        long id=0;
        try(Connection connection = SQLConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_GET_MAX_ID);
            while (resultSet.next()){
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        id+=1;
        return id;
    }
}
