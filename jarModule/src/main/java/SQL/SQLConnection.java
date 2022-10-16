package SQL;

import model.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName(Constants.JDBC_DRIVER);
            connection = DriverManager.getConnection(Constants.DATABASE_URL,"root","gfdj3lkm,.b31?");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
