package edu.radyuk.foodblog.dao.mapper;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.impl.BloggerInfoRowMapperImpl;
import edu.radyuk.foodblog.entity.BloggerInfo;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

class BloggerInfoRowMapperTest {
    private static BloggerInfo expected;

    @BeforeAll
    static void init() {
        expected = new BloggerInfo();
        expected.setUserId(1);
        expected.setBloggerAge(20);
        expected.setCity("City");
        expected.setCountry("Country");
        expected.setPersonalInfo("Personal info");
        expected.setAvatarPath("1.png");
    }

    @Test
    void ifBloggerInfoRowMapperReturnsValidObject() throws SQLException {
        ResultSet resultSet = EasyMock.mock(ResultSet.class);
        EasyMock.expect(resultSet.getLong(TableColumnName.USERS_USER_ID)).andReturn(expected.getUserId());
        EasyMock.expect(resultSet.getString(TableColumnName.BLOGGER_PICTURE)).andReturn(expected.getAvatarPath());
        EasyMock.expect(resultSet.getString(TableColumnName.BLOGGER_CITY)).andReturn(expected.getCity());
        EasyMock.expect(resultSet.getInt(TableColumnName.BLOGGER_AGE)).andReturn(expected.getBloggerAge());
        EasyMock.expect(resultSet.getString(TableColumnName.BLOGGER_COUNTRY)).andReturn(expected.getCountry());
        EasyMock.expect(resultSet.getString(TableColumnName.PERSONAL_INFO)).andReturn(expected.getPersonalInfo());
        EasyMock.replay(resultSet);

        RowMapper<BloggerInfo> bloggerInfoRowMapper = new BloggerInfoRowMapperImpl();
        BloggerInfo actual = bloggerInfoRowMapper.mapRow(resultSet);
        Assertions.assertEquals(actual, expected);
    }
}
