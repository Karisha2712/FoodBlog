package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    List<User> findAllUnapprovedUsers() throws DaoException;

    List<User> findAllApprovedUsers() throws DaoException;

    long updateUserStatus(long userId, UserStatus status) throws DaoException;
}
