package connectors;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.Constants;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnectors {

    private ComboPooledDataSource comboPooledDataSource;
    private static DataSourceConnectors dataSourceConnectors;
    private DataSourceConnectors() {
        String driver = Constants.JDBC_DRIVER;
        String url = Constants.DATABASE_URL;
        String user = "root";
        String password = "root";

        comboPooledDataSource = new ComboPooledDataSource();
        try {


            comboPooledDataSource.setDriverClass(driver);
            comboPooledDataSource.setJdbcUrl(url);
            comboPooledDataSource.setUser(user);
            comboPooledDataSource.setPassword(password);

            comboPooledDataSource.setMinPoolSize(5);
            comboPooledDataSource.setMaxPoolSize(20);
            comboPooledDataSource.setAcquireIncrement(5);
            comboPooledDataSource.setMaxStatements(100);
        }catch (PropertyVetoException e){
            throw new RuntimeException(e);
        }


    }

    public static DataSourceConnectors getInstance()  {

        if(dataSourceConnectors==null){

            dataSourceConnectors = new DataSourceConnectors();

        }
        return dataSourceConnectors;
    }

    public Connection getConnection() throws SQLException {
        return this.comboPooledDataSource.getConnection();
    }

}
