package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.BloggerInfoDao;
import edu.radyuk.foodblog.dao.JdbcHelper;
import edu.radyuk.foodblog.dao.mapper.impl.BloggerInfoRowMapperImpl;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BloggerInfoDaoImpl implements BloggerInfoDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String FIND_BLOGGER_INFO_BY_USER_ID =
            "SELECT * FROM blogger_infos WHERE users_user_id = ?";
    private JdbcHelper<BloggerInfo> jdbcHelper;

    public BloggerInfoDaoImpl() {
        jdbcHelper = new JdbcHelper<>(ConnectionPool.getInstance(), new BloggerInfoRowMapperImpl());
    }


    @Override
    public List<BloggerInfo> findAll() throws DaoException {
        return null;
        //TODO
    }

    @Override
    public Optional<BloggerInfo> findEntityById(long id) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_BLOGGER_INFO_BY_USER_ID, id);
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
