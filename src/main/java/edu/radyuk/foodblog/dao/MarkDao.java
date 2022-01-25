package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.Mark;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.Optional;

public interface MarkDao extends Dao<Mark> {
    Optional<Mark> findPostMark(long userId, long postId) throws DaoException;
}
