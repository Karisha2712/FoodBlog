package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.BloggerInfoDao;
import edu.radyuk.foodblog.dao.JdbcHelper;
import edu.radyuk.foodblog.dao.mapper.impl.BloggerInfoRowMapperImpl;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.Optional;

/**
 * The type Blogger info dao.
 */
public class BloggerInfoDaoImpl implements BloggerInfoDao {
    private static final String FIND_BLOGGER_INFO_BY_USER_ID =
            "SELECT * FROM blogger_infos WHERE users_user_id = ?";
    private static final String INSERT_BLOGGER_INFO_QUERY =
            "INSERT INTO blogger_infos VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BLOGGER_INFO_QUERY =
            "UPDATE blogger_infos SET picture = ?, age = ?, country = ?, " +
                    "city = ?, personal_info = ? where users_user_id = ?";
    private JdbcHelper<BloggerInfo> jdbcHelper;

    /**
     * Instantiates a new Blogger info dao.
     */
    public BloggerInfoDaoImpl() {
        jdbcHelper = new JdbcHelper<>(ConnectionPool.getInstance(), new BloggerInfoRowMapperImpl());
    }

    @Override
    public Optional<BloggerInfo> findEntityById(long id) {
        throw new UnsupportedOperationException("Method is not realised");
    }

    @Override
    public Optional<BloggerInfo> findBloggerInfoByUserId(long userId) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_BLOGGER_INFO_BY_USER_ID, userId);
    }

    @Override
    public long insert(BloggerInfo bloggerInfo) throws DaoException {
        return jdbcHelper.executeUpdate(INSERT_BLOGGER_INFO_QUERY,
                bloggerInfo.getAvatarPath(),
                bloggerInfo.getBloggerAge(),
                bloggerInfo.getCountry(),
                bloggerInfo.getCity(),
                bloggerInfo.getPersonalInfo(),
                bloggerInfo.getUserId());
    }

    @Override
    public long update(BloggerInfo bloggerInfo) throws DaoException {
        return jdbcHelper.executeUpdate(UPDATE_BLOGGER_INFO_QUERY,
                bloggerInfo.getAvatarPath(),
                bloggerInfo.getBloggerAge(),
                bloggerInfo.getCountry(),
                bloggerInfo.getCity(),
                bloggerInfo.getPersonalInfo(),
                bloggerInfo.getUserId());
    }

    @Override
    public long removeEntityById(long id) {
        throw new UnsupportedOperationException("Method is not realised");
    }
}
