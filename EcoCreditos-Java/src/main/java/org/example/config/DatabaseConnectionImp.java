package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnectionImp implements DatabaseConnection{

    private static DatabaseConnectionImp instance;

    private static Connection connection;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private DatabaseConnectionImp() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                        DatabaseConfig.getUrl(),
                        DatabaseConfig.getUser(),
                        DatabaseConfig.getPassword()
            );
        } catch (ClassNotFoundException e) {
            this.logger.severe("driver do oracle não foi localizado");
        }
    }

    static synchronized DatabaseConnectionImp getInstance() throws SQLException {
        if(instance == null || connection.isClosed()){
            instance = new DatabaseConnectionImp();
        }
        return instance;
    }

    @Override
    public Connection get() throws SQLException {
        connection.setAutoCommit(false);
        return connection;
    }
}
