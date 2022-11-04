package userImpl;

import connectors.DataSourceConnectors;
import dao.AbstractJPADAO;
import dao.UserDAO;
import exceptions.UserLogicException;
import jakarta.persistence.RollbackException;
import model.Constants;
import model.User;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractJPADAO implements UserDAO {


    @Override
    public List<User> getAllUsers() throws SQLException, PropertyVetoException {
        List<User> users = new ArrayList<>();
        User user=null;
        try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_GET_ALL_USERS);
            while (resultSet.next()){
                user = resultSetToUser(resultSet);
                users.add(user);
            }
        }


        return users;
    }

    @Override
    public User getByUsername(String username) throws SQLException, PropertyVetoException {

        User user=null;

        try (Connection connection = DataSourceConnectors.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_GET_BY_NAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = resultSetToUser(resultSet);
            }

        }

        return user;
    }

    @Override
    public User getByEmail(String email) throws PropertyVetoException, SQLException {
       User user = null;
       try(Connection connection = DataSourceConnectors.getInstance().getConnection()) {
           PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_GET_BY_EMAIL);
           preparedStatement.setString(1,email);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               user = resultSetToUser(resultSet);
           }
       }
        return user;
    }

    @Override
    public void delete(long id)  {
        init();
        User deleteUser = entityManager.find(User.class,id);
        entityManager.remove(deleteUser);
        close();

    }


    @Override
    public void add(User user) throws UserLogicException {

        user.setRole("user");
        try {
            init();
            entityManager.persist(user);
            close();
        }catch (RollbackException e){
            entityManager.getTransaction().rollback();
            throw new UserLogicException(e);
        }

    }

    @Override
    public void modify(User user) throws UserLogicException {

        try {
            init();
            entityManager.merge(user);
            close();
        }catch (RollbackException e){
            entityManager.getTransaction().rollback();
            throw new UserLogicException(e);
        }

    }

    @Override
    public User get(long ID)  {

        init();
        User user = entityManager.find(User.class,ID);
        close();
        return user;

    }
    private User resultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();

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

        return user;
    }
}
