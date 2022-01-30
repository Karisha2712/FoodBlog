package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.BloggerInfoDao;
import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class BloggerInfoServiceImpl implements BloggerInfoService {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_PICTURE_PATH = "default.png";


    @Override
    public String findPicturePathByUserId(long userId) throws ServiceException {
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            Optional<BloggerInfo> optionalBloggerInfo = bloggerInfoDao.findEntityById(userId);
            if (optionalBloggerInfo.isEmpty()) {
                logger.log(Level.ERROR, "There is no blogger info for this user");
                return DEFAULT_PICTURE_PATH;
            }
            String picturePath = optionalBloggerInfo.get().getAvatarPath();
            return picturePath.isBlank() ? DEFAULT_PICTURE_PATH : picturePath;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<BloggerInfo> findBloggerInfoByUserId(long userId) throws ServiceException {
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            return bloggerInfoDao.findEntityById(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }
}
