package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.mapper.RowMapper;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.DataBaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcHelper<T> {
    private static final Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool;
    private RowMapper<T> mapper;

    public JdbcHelper(ConnectionPool connectionPool, RowMapper<T> mapper) {
        this.connectionPool = connectionPool;
        this.mapper = mapper;
    }

    public List<T> executeQuery(String query, Object... parameters) throws DaoException {
        List<T> queryResult = new ArrayList<>();
        try (Connection connection = connectionPool.acquireConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            fillPreparedStatementParameters(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = mapper.mapRow(resultSet);
                queryResult.add(entity);
            }

        } catch (SQLException | DataBaseConnectionException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return queryResult;
    }

    public Optional<T> executeQueryForSingleObject(String query, Object... parameters) throws DaoException {
        return executeQuery(query, parameters)
                .stream()
                .findFirst();
    }

    public long executeUpdate(String query, Object... parameters) throws DaoException {
        try (Connection connection = connectionPool.acquireConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            fillPreparedStatementParameters(statement, parameters);
            return statement.executeUpdate();
        } catch (SQLException | DataBaseConnectionException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    private void fillPreparedStatementParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 1; i <= parameters.length; i++) {
            statement.setObject(i, parameters[i - 1]);
        }
    }
}
