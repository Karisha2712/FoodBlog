package edu.radyuk.foodblog.dao.mapper.impl;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.RowMapper;
import edu.radyuk.foodblog.entity.BloggerInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BloggerInfoRowMapperImpl implements RowMapper<BloggerInfo> {
    @Override
    public BloggerInfo mapRow(ResultSet resultSet) throws SQLException {
        BloggerInfo bloggerInfo = new BloggerInfo();
        bloggerInfo.setUserId(resultSet.getLong(TableColumnName.USERS_USER_ID));
        bloggerInfo.setAvatarPath(resultSet.getString(TableColumnName.BLOGGER_PICTURE));
        bloggerInfo.setCity(resultSet.getString(TableColumnName.BLOGGER_CITY));
        bloggerInfo.setBloggerAge(resultSet.getInt(TableColumnName.BLOGGER_AGE));
        bloggerInfo.setCountry(resultSet.getString(TableColumnName.BLOGGER_COUNTRY));
        bloggerInfo.setPersonalInfo(resultSet.getString(TableColumnName.PERSONAL_INFO));
        return bloggerInfo;
    }
}
