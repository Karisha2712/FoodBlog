package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() throws DaoException {
        return null;
        //TODO
    }

    @Override
    public Optional<User> findEntityById(long id) throws DaoException {
        return Optional.empty();
        //TODO
    }

    @Override
    public long insert(User entity) throws DaoException {
        return 0;
        //TODO
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
}
