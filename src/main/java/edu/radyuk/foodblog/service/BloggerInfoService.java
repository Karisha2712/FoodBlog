package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.entity.dto.BloggerInfoDto;
import edu.radyuk.foodblog.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

/**
 * The interface Blogger info service.
 */
public interface BloggerInfoService {
    /**
     * Retrieve picture path by user id string.
     *
     * @param userId the user id
     * @return the string
     * @throws ServiceException the service exception
     */
    String retrievePicturePathByUserId(long userId) throws ServiceException;

    /**
     * Retrieve blogger info by user id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<BloggerInfoDto> retrieveBloggerInfoByUserId(long userId) throws ServiceException;

    /**
     * Add default blogger info long.
     *
     * @param userId the user id
     * @return the long
     * @throws ServiceException the service exception
     */
    long addDefaultBloggerInfo(long userId) throws ServiceException;

    /**
     * Refresh blogger info blogger info.
     *
     * @param city         the city
     * @param country      the country
     * @param age          the age
     * @param personalInfo the personal info
     * @param userId       the user id
     * @param pictureParts the picture parts
     * @return the blogger info
     * @throws ServiceException the service exception
     */
    BloggerInfo refreshBloggerInfo(String city, String country, int age,
                                   String personalInfo, long userId, List<Part> pictureParts) throws ServiceException;
}
