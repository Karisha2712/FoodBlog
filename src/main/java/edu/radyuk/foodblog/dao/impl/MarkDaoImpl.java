package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.JdbcHelper;
import edu.radyuk.foodblog.dao.MarkDao;
import edu.radyuk.foodblog.dao.mapper.impl.MarkRowMapperImpl;
import edu.radyuk.foodblog.entity.Mark;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class MarkDaoImpl implements MarkDao {
    private static final String FIND_POST_MARK_QUERY = "SELECT * FROM marks " +
            "WHERE users_user_id = ? AND posts_post_id = ?";
    private JdbcHelper<Mark> jdbcHelper;

    public MarkDaoImpl() {
        jdbcHelper = new JdbcHelper<>(ConnectionPool.getInstance(), new MarkRowMapperImpl());
    }


    @Override
    public List<Mark> findAll() throws DaoException {
        return null;
        //TODO
    }

    @Override
    public Optional<Mark> findEntityById(long id) throws DaoException {
        return Optional.empty();
        //TODO
    }

    @Override
    public long insert(Mark entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long update(Mark entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long remove(Mark entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long removeEntityById(long id) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public Optional<Mark> findPostMark(long userId, long postId) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_POST_MARK_QUERY, userId, postId);
    }
}
