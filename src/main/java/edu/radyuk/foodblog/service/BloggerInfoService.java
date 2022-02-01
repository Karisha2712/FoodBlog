package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface BloggerInfoService {
    String retrievePicturePathByUserLogin(String userLogin) throws ServiceException;

    Optional<BloggerInfo> retrieveBloggerInfoByUserLogin(String userLogin) throws ServiceException;

    long addBloggerInfo(BloggerInfo bloggerInfo) throws ServiceException;

    long refreshBloggerInfo(BloggerInfo bloggerInfo) throws ServiceException;

    String saveUserAvatar(long userId, List<Part> pictureParts) throws ServiceException;
}
