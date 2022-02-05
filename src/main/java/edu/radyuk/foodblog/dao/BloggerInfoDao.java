package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.Optional;

/**
 * The interface Blogger info dao.
 */
public interface BloggerInfoDao extends Dao<BloggerInfo> {
    /**
     * Find blogger info by user id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<BloggerInfo> findBloggerInfoByUserId(long userId) throws DaoException;
}
