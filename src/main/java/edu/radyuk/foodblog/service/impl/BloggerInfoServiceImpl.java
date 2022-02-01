package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.controller.command.DefaultValues;
import edu.radyuk.foodblog.dao.BloggerInfoDao;
import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class BloggerInfoServiceImpl implements BloggerInfoService {
    private static final Logger logger = LogManager.getLogger();
    private static final String PICTURE_PROPERTIES = "property/picture.properties";
    private static final String AVATAR_PATH_PROPERTY = "picture.avatar";
    private static final String BASE_DIRECTORY_PROPERTY = "picture.base_directory";
    private static final String DEFAULT_BASE_DIRECTORY = "D:/blog_pictures/";
    private static final String DEFAULT_AVATAR_PATH_PROPERTY = "avatars/";
    private static final String EXTENSION = ".png";
    private String baseDirectory;
    private String avatarPath;

    public BloggerInfoServiceImpl() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Properties properties = new Properties();
            properties.load(classLoader.getResourceAsStream(PICTURE_PROPERTIES));
            baseDirectory = properties.getProperty(BASE_DIRECTORY_PROPERTY);
            avatarPath = properties.getProperty(AVATAR_PATH_PROPERTY);
            if (baseDirectory == null || avatarPath == null) {
                baseDirectory = DEFAULT_BASE_DIRECTORY;
                avatarPath = DEFAULT_AVATAR_PATH_PROPERTY;
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading property file {} ", PICTURE_PROPERTIES, e);
            baseDirectory = DEFAULT_BASE_DIRECTORY;
            avatarPath = DEFAULT_AVATAR_PATH_PROPERTY;
        }
    }

    @Override
    public String retrievePicturePathByUserLogin(String userLogin) throws ServiceException {
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            Optional<BloggerInfo> optionalBloggerInfo = bloggerInfoDao.findBloggerInfoByUserLogin(userLogin);
            if (optionalBloggerInfo.isEmpty()) {
                logger.log(Level.ERROR, "There is no blogger info for this user");
                return DefaultValues.DEFAULT_AVATAR;
            }
            String picturePath = optionalBloggerInfo.get().getAvatarPath();
            return picturePath.isBlank() ? DefaultValues.DEFAULT_AVATAR : picturePath;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<BloggerInfo> retrieveBloggerInfoByUserLogin(String userLogin) throws ServiceException {
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            return bloggerInfoDao.findBloggerInfoByUserLogin(userLogin);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long addBloggerInfo(BloggerInfo bloggerInfo) throws ServiceException {
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            return bloggerInfoDao.insert(bloggerInfo);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long refreshBloggerInfo(BloggerInfo bloggerInfo) throws ServiceException {
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            return bloggerInfoDao.update(bloggerInfo);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public String saveUserAvatar(long userId, List<Part> pictureParts) throws ServiceException {
        String relativePicturePath = avatarPath + userId + EXTENSION;
        String absolutePath = baseDirectory + relativePicturePath;
        try (FileOutputStream fileOutputStream = new FileOutputStream(absolutePath)) {
            for (Part part : pictureParts) {
                part.getInputStream().transferTo(fileOutputStream);
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return relativePicturePath;
    }
}
