package model;

public class Constants {

    private Constants(){

    }
    public static final String FILE_PATH = "D:\\projects\\it-academy\\test1.csv";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/it-academy";

    public static final String SQL_GET_BY_ID = "SELECT * FROM user where ID = ?";
    public static final String SQL_GET_BY_NAME = "SELECT * FROM user where name = ?";
    public static final String SQL_GET_BY_EMAIL = "SELECT * FROM user where email = ?";
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_GET_MAX_ID = "SELECT MAX(ID) FROM user";



}
