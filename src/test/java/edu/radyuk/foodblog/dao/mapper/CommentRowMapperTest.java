package edu.radyuk.foodblog.dao.mapper;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.impl.CommentRowMapperImpl;
import edu.radyuk.foodblog.entity.Comment;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

class CommentRowMapperTest {
    private static Comment expected;

    @BeforeAll
    static void init() {
        expected = new Comment();
        expected.setUserId(1);
        expected.setPostId(1);
        expected.setMark(3);
        expected.setEntityId(1);
        expected.setCommentText("Comment text");
        expected.setCommentDate(LocalDateTime.now());
    }

    @Test
    void ifCommentRowMapperReturnsValidObject() throws SQLException {
        ResultSet resultSet = EasyMock.mock(ResultSet.class);
        EasyMock.expect(resultSet.getLong(TableColumnName.COMMENT_ID)).andReturn(expected.getEntityId());
        EasyMock.expect(resultSet.getString(TableColumnName.COMMENT_TEXT)).andReturn(expected.getCommentText());
        EasyMock.expect(resultSet.getLong(TableColumnName.POSTS_POST_ID)).andReturn(expected.getPostId());
        EasyMock.expect(resultSet.getLong(TableColumnName.USERS_USER_ID)).andReturn(expected.getUserId());
        EasyMock.expect(resultSet.getDouble(TableColumnName.MARK)).andReturn(expected.getMark());
        EasyMock.expect(resultSet.getTimestamp(TableColumnName.COMMENT_DATE))
                .andReturn(Timestamp.valueOf(expected.getCommentDate()));
        EasyMock.replay(resultSet);

        RowMapper<Comment> commentRowMapper = new CommentRowMapperImpl();
        Comment actual = commentRowMapper.mapRow(resultSet);
        Assertions.assertEquals(actual, expected);
    }
}
