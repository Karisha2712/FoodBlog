package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.dao.RecipePostDao;
import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;

public class RecipePostDaoImpl implements RecipePostDao {
    @Override
    public List<RecipePost> findAll() throws DaoException {
        return null;
        //TODO
    }

    @Override
    public RecipePost findEntityById(long id) throws DaoException {
        return null;
        //TODO
    }

    @Override
    public long insert(RecipePost entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long update(RecipePost entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long remove(RecipePost entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long removeEntityById(long id) throws DaoException {
        return 0;
        //TODO
    }
}
