package edu.radyuk.foodblog.dao.mapper.impl;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.RowMapper;
import edu.radyuk.foodblog.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentRowMapperImpl implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setEntityId(resultSet.getLong(TableColumnName.COMMENT_ID));
        comment.setCommentText(resultSet.getString(TableColumnName.COMMENT_TEXT));
        comment.setPostId(resultSet.getLong(TableColumnName.POSTS_POST_ID));
        comment.setUserId(resultSet.getLong(TableColumnName.USERS_USER_ID));
        comment.setMark(resultSet.getDouble(TableColumnName.MARK));
        LocalDateTime dateTime = resultSet.getTimestamp(TableColumnName.COMMENT_DATE).toLocalDateTime();
        comment.setCommentDate(dateTime);
        return comment;
    }
}
