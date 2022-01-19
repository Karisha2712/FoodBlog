package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    public Optional<User> findUserByLogin(String login) throws DaoException;

    public Optional<User> findUserByEmail(String email) throws DaoException;
}
