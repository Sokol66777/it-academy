package model;

public class Constants {

    private Constants(){

    }
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/it-academy";
    public static final String SQL_GET_BY_ID = "SELECT * FROM user where ID = ?";
    public static final String SQL_GET_BY_NAME = "SELECT * FROM user where name = ?";
    public static final String SQL_GET_BY_EMAIL = "SELECT * FROM user where email = ?";
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_DELETE_FROM_USER = "DELETE FROM user WHERE name = ?";
    public static final String SQL_ADD_INTO_USER = "INSERT INTO user(name,password,email) VALUES (?,?,?)";
    public static final String SQL_UPDATE_USER_WHITH_PASSWORD = "UPDATE user SET name = ?, password = ?, email = ? WHERE name = ?";
    public static final String SQL_UPDATE_USER_WHITHOUT_PASSWORD = "UPDATE user SET name = ?, email = ? WHERE name = ?";



}
