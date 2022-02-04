package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User signUp(String login, String password, String email, UserRole role, UserStatus status) throws ServiceException;

    List<User> retrieveUnapprovedUsers() throws ServiceException;

    List<User> retrieveApprovedUsers() throws ServiceException;

    Optional<User> retrieveUserIfExists(String password, String email) throws ServiceException;

    Optional<User> retrieveUserById(long userId) throws ServiceException;

    Optional<String> retrieveUserLoginByUserId(long userId) throws ServiceException;

    long refreshUserStatus(long userId, UserStatus status) throws ServiceException;

    boolean isLoginAvailable(String login) throws ServiceException;

    boolean isEmailAvailable(String email) throws ServiceException;
}
