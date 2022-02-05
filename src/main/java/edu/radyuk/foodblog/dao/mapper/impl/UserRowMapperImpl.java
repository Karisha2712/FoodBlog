package edu.radyuk.foodblog.dao.mapper.impl;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.RowMapper;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * The type User row mapper.
 */
public class UserRowMapperImpl implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEntityId(resultSet.getLong(TableColumnName.USER_ID));
        user.setEmail(resultSet.getString(TableColumnName.EMAIL));
        user.setLogin(resultSet.getString(TableColumnName.LOGIN));
        user.setPasswordHash(resultSet.getString(TableColumnName.PASSWORD_HASH));
        String status = resultSet.getString(TableColumnName.STATUS).toUpperCase(Locale.ROOT);
        user.setUserStatus(UserStatus.valueOf(status));
        String role = resultSet.getString(TableColumnName.TYPE).toUpperCase(Locale.ROOT);
        user.setUserRole(UserRole.valueOf(role));
        return user;
    }
}
