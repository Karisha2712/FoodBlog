package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;

public interface UserService {
    User signUp(String login, String password, String email, UserRole role, UserStatus status) throws ServiceException;

    boolean isLoginAvailable(String login) throws ServiceException;

    boolean isEmailAvailable(String email) throws ServiceException;
}
