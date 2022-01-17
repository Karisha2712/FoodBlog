package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.dao.CommentDao;
import edu.radyuk.foodblog.entity.Comment;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class CommentDaoImpl implements CommentDao {
    @Override
    public List<Comment> findAll() throws DaoException {
        return null;
        //TODO
    }

    @Override
    public Optional<Comment> findEntityById(long id) throws DaoException {
        return Optional.empty();
        //TODO
    }

    @Override
    public long insert(Comment entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long update(Comment entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long remove(Comment entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long removeEntityById(long id) throws DaoException {
        return 0;
        //TODO
    }
}
