package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface BloggerInfoService {
    String retrievePicturePathByUserLogin(String userLogin) throws ServiceException;

    Optional<BloggerInfo> retrieveBloggerInfoByUserLogin(String userLogin) throws ServiceException;

    long addDefaultBloggerInfo(String userLogin) throws ServiceException;

    BloggerInfo refreshBloggerInfo(String city, String country, int age,
                            String personalInfo, String userLogin, long userId, List<Part> pictureParts) throws ServiceException;
}
