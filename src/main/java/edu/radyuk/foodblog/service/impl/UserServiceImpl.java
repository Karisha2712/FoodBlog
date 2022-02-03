package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.DaoProvider;
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

import java.util.List;
import java.util.Optional;

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
        UserDao userDao = DaoProvider.getInstance().getUserDao();
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
    public List<User> retrieveUnapprovedUsers() throws ServiceException {
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.findAllUnapprovedUsers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> retrieveApprovedUsers() throws ServiceException {
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.findAllApprovedUsers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> retrieveUserIfExists(String password, String email) throws ServiceException {
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isEmpty()) {
                return Optional.empty();
            }
            User user = optionalUser.get();
            String passwordHash = DigestUtils.sha256Hex(password);
            if (!(passwordHash.equals(user.getPasswordHash()))) {
                return Optional.empty();
            }
            return optionalUser;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> retrieveUserLoginByUserId(long userId) throws ServiceException {
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            Optional<User> optionalUser = userDao.findEntityById(userId);
            if (optionalUser.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(optionalUser.get().getLogin());
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long refreshUserStatus(long userId, UserStatus status) throws ServiceException {
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.updateUserStatus(userId, status);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isLoginAvailable(String login) throws ServiceException {
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.findUserByLogin(login).isEmpty();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.findUserByEmail(email).isEmpty();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }
}
