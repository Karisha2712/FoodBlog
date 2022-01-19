package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.DaoFactory;
import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public User signUp(String login, String password, String email, UserRole role, UserStatus status)
            throws ServiceException {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setUserRole(role);
        user.setUserStatus(status);
        String passwordHash = DigestUtils.sha256Hex(password);
        user.setPasswordHash(passwordHash);
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        long id;
        try {
            id = userDao.insert(user);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        user.setEntityId(id);
        return user;
    }

    @Override
    public boolean isLoginAvailable(String login) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            return userDao.findUserByLogin(login).isEmpty();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            return userDao.findUserByEmail(email).isEmpty();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }
}