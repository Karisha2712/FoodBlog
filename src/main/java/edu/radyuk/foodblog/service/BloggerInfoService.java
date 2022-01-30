package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.ServiceException;

import java.util.Optional;

public interface BloggerInfoService {
    String findPicturePathByUserId(long userId) throws ServiceException;

    Optional<BloggerInfo> findBloggerInfoByUserId(long userId) throws ServiceException;
}
