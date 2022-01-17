package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.dao.BloggerInfoDao;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class BloggerInfoDaoImpl implements BloggerInfoDao {
    @Override
    public List<BloggerInfo> findAll() throws DaoException {
        return null;
        //TODO
    }

    @Override
    public Optional<BloggerInfo> findEntityById(long id) throws DaoException {
        return Optional.empty();
        //TODO
    }

    @Override
    public long insert(BloggerInfo entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long update(BloggerInfo entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long remove(BloggerInfo entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long removeEntityById(long id) throws DaoException {
        return 0;
        //TODO
    }
}
