package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.ServiceException;

import java.util.Optional;

public interface BloggerInfoService {
    String retrievePicturePathByUserLogin(String userLogin) throws ServiceException;

    Optional<BloggerInfo> retrieveBloggerInfoByUserLogin(String userLogin) throws ServiceException;

    long addBloggerInfo(BloggerInfo bloggerInfo) throws ServiceException;
}
