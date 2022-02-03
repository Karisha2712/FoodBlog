package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.BloggerInfoDao;
import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import edu.radyuk.foodblog.service.PictureLoadingService;
import edu.radyuk.foodblog.service.ServiceProvider;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public class BloggerInfoServiceImpl implements BloggerInfoService {
    private static final String DEFAULT_AVATAR = "default_avatar_big.png";
    private static final int DEFAULT_AGE = 0;
    private static final String DEFAULT_CITY = "City";
    private static final String DEFAULT_COUNTRY = "Country";
    private static final String DEFAULT_INFO = "Some info";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String retrievePicturePathByUserLogin(String userLogin) throws ServiceException {
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            Optional<BloggerInfo> optionalBloggerInfo = bloggerInfoDao.findBloggerInfoByUserLogin(userLogin);
            if (optionalBloggerInfo.isEmpty()) {
                logger.log(Level.ERROR, "There is no blogger info for this user");
                return DEFAULT_AVATAR;
            }
            String picturePath = optionalBloggerInfo.get().getAvatarPath();
            return picturePath.isBlank() ? DEFAULT_AVATAR : picturePath;
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
    public long addDefaultBloggerInfo(String userLogin) throws ServiceException {
        BloggerInfo bloggerInfo = new BloggerInfo();
        bloggerInfo.setBloggerAge(DEFAULT_AGE);
        bloggerInfo.setUserLogin(userLogin);
        bloggerInfo.setPersonalInfo(DEFAULT_INFO);
        bloggerInfo.setAvatarPath(DEFAULT_AVATAR);
        bloggerInfo.setCountry(DEFAULT_COUNTRY);
        bloggerInfo.setCity(DEFAULT_CITY);
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            return bloggerInfoDao.insert(bloggerInfo);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public BloggerInfo refreshBloggerInfo(String city, String country, int age, String personalInfo,
                                          String userLogin, long userId, List<Part> pictureParts) throws ServiceException {
        BloggerInfo bloggerInfo = new BloggerInfo();
        bloggerInfo.setCity(city);
        bloggerInfo.setCountry(country);
        bloggerInfo.setBloggerAge(age);
        personalInfo = StringEscapeUtils.escapeHtml4(personalInfo);
        bloggerInfo.setPersonalInfo(personalInfo);
        bloggerInfo.setUserLogin(userLogin);
        PictureLoadingService pictureLoadingService = ServiceProvider.getInstance().getPictureLoadingService();
        String avatarPath = pictureLoadingService.savePicture(userId,
                PictureLoadingServiceImpl.PictureCategory.AVATAR, pictureParts);
        bloggerInfo.setAvatarPath(avatarPath);
        BloggerInfoDao bloggerInfoDao = DaoProvider.getInstance().getBloggerInfoDao();
        try {
            bloggerInfoDao.update(bloggerInfo);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return bloggerInfo;
    }
}
