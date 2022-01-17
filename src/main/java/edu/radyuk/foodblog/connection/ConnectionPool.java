package edu.radyuk.foodblog.connection;

import edu.radyuk.foodblog.exception.DataBaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 4;
    private static final String POOL_PROPERTIES_FILE = "properties/pool.properties";
    private static final String POOL_SIZE_PROPERTY = "pool.size";
    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> usedConnections;

    private ConnectionPool() {
        int poolSize = retrievePoolSize();
        availableConnections = new ArrayBlockingQueue<>(poolSize);
        usedConnections = new ArrayBlockingQueue<>(poolSize);
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = connectionFactory.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.offer(proxyConnection);

            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error while connecting DB ", e);
            }
        }
        if (availableConnections.isEmpty()) {
            logger.log(Level.ERROR, "Errors while creating connections: no connections were created");
            throw new RuntimeException("Errors while creating connections: no connections were created");
        }
        logger.log(Level.INFO, "ConnectionPool was created successfully ");
    }

    public static ConnectionPool getInstance() {
        return Holder.instance;
    }

    public Connection acquireConnection() throws DataBaseConnectionException {
        ProxyConnection connection;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.ERROR, "Thread was interrupted", e);
            throw new DataBaseConnectionException("Thread was interrupted ", e);
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) throws DataBaseConnectionException {
        if (!(connection instanceof ProxyConnection)) {
            return false;
        }
        try {
            usedConnections.remove(connection);
            availableConnections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.ERROR, "Thread was interrupted", e);
            throw new DataBaseConnectionException("Thread was interrupted ", e);
        }
        return true;
    }

    public void closeConnections() {
        availableConnections.forEach(ProxyConnection::reallyClose);
        usedConnections.forEach(ProxyConnection::reallyClose);
        logger.log(Level.INFO, "Connections were closed");
    }

    private int retrievePoolSize() {
        int poolSize = DEFAULT_POOL_SIZE;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(POOL_PROPERTIES_FILE)) {
            Properties properties = new Properties();
            if (inputStream == null) {
                logger.log(Level.WARN, "File pool.properties does not exist, default pool size is used");
                return DEFAULT_POOL_SIZE;
            }
            properties.load(inputStream);
            poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE_PROPERTY));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while loading pool properties, default pool size is used ", e);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "Invalid pool-size property format, default size is used ", e);
        }
        return poolSize;
    }

    private static class Holder {
        private static final ConnectionPool instance = new ConnectionPool();
    }
}