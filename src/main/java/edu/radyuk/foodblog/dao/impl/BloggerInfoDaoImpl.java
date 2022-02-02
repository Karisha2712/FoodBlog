package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.BloggerInfoDao;
import edu.radyuk.foodblog.dao.JdbcHelper;
import edu.radyuk.foodblog.dao.mapper.impl.BloggerInfoRowMapperImpl;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class BloggerInfoDaoImpl implements BloggerInfoDao {
    private static final String FIND_BLOGGER_INFO_BY_USER_LOGIN =
            "SELECT * FROM blogger_infos WHERE users_login = ?";
    private static final String INSERT_BLOGGER_INFO_QUERY =
            "INSERT INTO blogger_infos VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BLOGGER_INFO_QUERY =
            "UPDATE blogger_infos SET picture = ?, age = ?, country = ?, city = ?, personal_info = ? where users_login = ?";
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
        return Optional.empty();
        //TODO
    }

    @Override
    public Optional<BloggerInfo> findBloggerInfoByUserLogin(String userLogin) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_BLOGGER_INFO_BY_USER_LOGIN, userLogin);
    }

    @Override
    public long insert(BloggerInfo bloggerInfo) throws DaoException {
        return jdbcHelper.executeUpdate(INSERT_BLOGGER_INFO_QUERY,
                bloggerInfo.getAvatarPath(),
                bloggerInfo.getBloggerAge(),
                bloggerInfo.getCountry(),
                bloggerInfo.getCity(),
                bloggerInfo.getPersonalInfo(),
                bloggerInfo.getUserLogin());
    }

    @Override
    public long update(BloggerInfo bloggerInfo) throws DaoException {
        return jdbcHelper.executeUpdate(UPDATE_BLOGGER_INFO_QUERY,
                bloggerInfo.getAvatarPath(),
                bloggerInfo.getBloggerAge(),
                bloggerInfo.getCountry(),
                bloggerInfo.getCity(),
                bloggerInfo.getPersonalInfo(),
                bloggerInfo.getUserLogin());
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
