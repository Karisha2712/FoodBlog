package edu.radyuk.foodblog.dao.mapper.impl;

import edu.radyuk.foodblog.dao.mapper.RowMapper;
import edu.radyuk.foodblog.entity.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.radyuk.foodblog.dao.TableColumnName.*;

public class MarkRowMapperImpl implements RowMapper<Mark> {
    @Override
    public Mark mapRow(ResultSet resultSet) throws SQLException {
        Mark mark = new Mark();
        mark.setUserId(resultSet.getLong(USERS_USER_ID));
        mark.setPostId(resultSet.getLong(POSTS_POST_ID));
        mark.setMarkValue(resultSet.getDouble(MARK));
        return mark;
    }
}
