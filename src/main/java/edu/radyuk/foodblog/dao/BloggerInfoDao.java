package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.Optional;

public interface BloggerInfoDao extends Dao<BloggerInfo> {
    Optional<BloggerInfo> findBloggerInfoByUserId(long userId) throws DaoException;
}
