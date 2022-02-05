package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao extends Dao<User> {
    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Find all unapproved users list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllUnapprovedUsers() throws DaoException;

    /**
     * Find all approved users list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllApprovedUsers() throws DaoException;

    /**
     * Update user status long.
     *
     * @param userId the user id
     * @param status the status
     * @return the long
     * @throws DaoException the dao exception
     */
    long updateUserStatus(long userId, UserStatus status) throws DaoException;
}
