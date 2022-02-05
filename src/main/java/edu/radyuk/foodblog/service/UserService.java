package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Sign up user.
     *
     * @param login    the login
     * @param password the password
     * @param email    the email
     * @param role     the role
     * @param status   the status
     * @return the user
     * @throws ServiceException the service exception
     */
    User signUp(String login, String password, String email, UserRole role, UserStatus status) throws ServiceException;

    /**
     * Retrieve unapproved users list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> retrieveUnapprovedUsers() throws ServiceException;

    /**
     * Retrieve approved users list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> retrieveApprovedUsers() throws ServiceException;

    /**
     * Retrieve user if exists optional.
     *
     * @param password the password
     * @param email    the email
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> retrieveUserIfExists(String password, String email) throws ServiceException;

    /**
     * Retrieve user by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> retrieveUserById(long userId) throws ServiceException;

    /**
     * Refresh user status long.
     *
     * @param userId the user id
     * @param status the status
     * @return the long
     * @throws ServiceException the service exception
     */
    long refreshUserStatus(long userId, UserStatus status) throws ServiceException;

    /**
     * Is login available boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isLoginAvailable(String login) throws ServiceException;

    /**
     * Is email available boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isEmailAvailable(String email) throws ServiceException;
}
