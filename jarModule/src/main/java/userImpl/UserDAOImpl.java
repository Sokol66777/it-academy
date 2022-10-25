package userImpl;

import connectors.DataSourceConnectors;
import dao.UserDAO;
import model.Constants;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
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
        }


        return users;
    }


    @Override
    public User getByUsername(String username) throws SQLException {
        User user=null;

        try (Connection connection = DataSourceConnectors.getInstance().getConnection()){
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

        }

        return user;
    }



    @Override
    public User getByEmail(String email) {
       User user = null;
       try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
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
    public void Delete(String username) throws SQLException {

        try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_DELETE_FROM_USER);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();
        }

    }


    @Override
    public void Add(User user) throws SQLException {

        try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_ADD_INTO_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void Modify(User user) throws SQLException {

        try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
            PreparedStatement preparedStatement;
            if(user.getPassword()!=null) {
                preparedStatement = connection.prepareStatement(Constants.SQL_UPDATE_USER_WHITH_PASSWORD);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setLong(4, user.getID());
            }else{

                preparedStatement = connection.prepareStatement(Constants.SQL_UPDATE_USER_WHITHOUT_PASSWORD);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setLong(3,user.getID());


            }
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public User get(long ID) throws SQLException {

        User user = null;
        try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
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
        }

        return user;
    }
}
