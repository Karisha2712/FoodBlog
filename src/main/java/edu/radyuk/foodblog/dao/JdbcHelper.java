package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.mapper.RowMapper;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.DataBaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Jdbc helper.
 *
 * @param <T> the type parameter
 */
public class JdbcHelper<T> {
    private static final Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool;
    private RowMapper<T> mapper;

    /**
     * Instantiates a new Jdbc helper.
     *
     * @param connectionPool the connection pool
     * @param mapper         the mapper
     */
    public JdbcHelper(ConnectionPool connectionPool, RowMapper<T> mapper) {
        this.connectionPool = connectionPool;
        this.mapper = mapper;
    }

    /**
     * Execute query list.
     *
     * @param query      the query
     * @param parameters the parameters
     * @return the list
     * @throws DaoException the dao exception
     */
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

    /**
     * Execute query for single object optional.
     *
     * @param query      the query
     * @param parameters the parameters
     * @return the optional
     * @throws DaoException the dao exception
     */
    public Optional<T> executeQueryForSingleObject(String query, Object... parameters) throws DaoException {
        return executeQuery(query, parameters)
                .stream()
                .findFirst();
    }

    /**
     * Execute update long.
     *
     * @param query      the query
     * @param parameters the parameters
     * @return the long
     * @throws DaoException the dao exception
     */
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

    /**
     * Execute insert long.
     *
     * @param query      the query
     * @param parameters the parameters
     * @return the long
     * @throws DaoException the dao exception
     */
    public long executeInsert(String query, Object... parameters) throws DaoException {
        try (Connection connection = connectionPool.acquireConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatementParameters(statement, parameters);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
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
