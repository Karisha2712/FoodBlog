package edu.radyuk.foodblog.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The interface Row mapper.
 *
 * @param <T> the type parameter
 */
public interface RowMapper<T> {
    /**
     * Map row t.
     *
     * @param resultSet the result set
     * @return the t
     * @throws SQLException the sql exception
     */
    T mapRow(ResultSet resultSet) throws SQLException;
}
