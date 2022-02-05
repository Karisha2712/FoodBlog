package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.entity.dto.BloggerInfoDto;
import edu.radyuk.foodblog.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface BloggerInfoService {
    String retrievePicturePathByUserId(long userId) throws ServiceException;

    Optional<BloggerInfoDto> retrieveBloggerInfoByUserId(long userId) throws ServiceException;

    long addDefaultBloggerInfo(long userId) throws ServiceException;

    BloggerInfo refreshBloggerInfo(String city, String country, int age,
                            String personalInfo, long userId, List<Part> pictureParts) throws ServiceException;
}
