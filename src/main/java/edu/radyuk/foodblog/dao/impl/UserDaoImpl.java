package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.JdbcHelper;
import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.dao.mapper.impl.UserRowMapperImpl;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users " +
            "JOIN user_types ON user_types_type_id = type_id " +
            "JOIN user_statuses ON user_statuses_status_id = status_id";
    private static final String FIND_USER_BY_ID_QUERY = FIND_ALL_USERS_QUERY + " WHERE user_id = ?";
    private static final String FIND_USER_BY_LOGIN_QUERY = FIND_ALL_USERS_QUERY + " WHERE login = ?";
    private static final String FIND_USER_BY_EMAIL_QUERY = FIND_ALL_USERS_QUERY + " WHERE email = ?";
    private static final String INSERT_NEW_USER = "INSERT INTO users " +
            "(login, email, hash, user_types_type_id, user_statuses_status_id) " +
            "VALUES(?, ?, ?, " +
            "(SELECT type_id FROM user_types WHERE type = ?), " +
            "(SELECT status_id FROM user_statuses WHERE status = ?))";
    private JdbcHelper<User> jdbcHelper;

    public UserDaoImpl() {
        jdbcHelper = new JdbcHelper<>(ConnectionPool.getInstance(), new UserRowMapperImpl());
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
        //TODO
    }

    @Override
    public Optional<User> findEntityById(long id) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_USER_BY_ID_QUERY, id);
    }

    @Override
    public long insert(User user) throws DaoException {
        return jdbcHelper.executeUpdate(INSERT_NEW_USER,
                user.getLogin(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getUserRole().toString(),
                user.getUserStatus().toString());
    }

    @Override
    public long update(User entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long remove(User entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long removeEntityById(long id) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_USER_BY_LOGIN_QUERY, login);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_USER_BY_EMAIL_QUERY, email);
    }
}
