package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.exception.ServiceException;

public interface BloggerInfoService {
    String findPicturePathByUserId(long userId) throws ServiceException;
}
