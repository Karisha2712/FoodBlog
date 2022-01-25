package edu.radyuk.foodblog.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

final class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionFactory instance = new ConnectionFactory();
    private static final String DB_PROPERTIES = "property/db.properties";
    private static final String DB_URL_PROPERTY = "url";
    private static final String DB_USER_PROPERTY = "user";
    private static final String DB_PASS_PROPERTY = "password";
    private static final String DB_DRIVER_PROPERTY = "driver";
    private Properties properties;
    private String dbUrl;

    private ConnectionFactory() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES)) {
            if (inputStream == null) {
                logger.log(Level.ERROR, "File db.properties does not exist");
                throw new RuntimeException("File db.properties does not exist");
            }
            properties.load(inputStream);
            dbUrl = properties.getProperty(DB_URL_PROPERTY);
            if (dbUrl == null) {
                logger.log(Level.ERROR, "Url property does not exist");
                throw new RuntimeException("Url property does not exist");
            }

            String dbUser = properties.getProperty(DB_USER_PROPERTY);
            if (dbUser == null) {
                logger.log(Level.ERROR, "User property does not exist");
                throw new RuntimeException("User property does not exist");
            }

            String dbPassword = properties.getProperty(DB_PASS_PROPERTY);
            if (dbPassword == null) {
                logger.log(Level.ERROR, "Password property does not exist");
                throw new RuntimeException("Password property does not exist");
            }

            String dbDriver = properties.getProperty(DB_DRIVER_PROPERTY);
            if (dbDriver == null) {
                logger.log(Level.ERROR, "Driver property does not exist");
                throw new RuntimeException("Driver property does not exist");
            }
            Class.forName(dbDriver);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while loading DB properties ", e);
            throw new RuntimeException("Error while loading DB properties ", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.ERROR, "JDBC driver not found ", e);
            throw new RuntimeException("JDBC driver not found ", e);
        }
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, properties);
    }
}