package edu.radyuk.foodblog.dao.mapper;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.impl.UserRowMapperImpl;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import org.apache.commons.codec.digest.DigestUtils;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

class UserRowMapperTest {
    private static User expected;

    @BeforeAll
    static void init() {
        expected = new User();
        expected.setEntityId(1);
        expected.setPasswordHash(DigestUtils.sha256Hex("12345"));
        expected.setLogin("Login");
        expected.setUserRole(UserRole.BLOGGER);
        expected.setUserStatus(UserStatus.ACTIVE);
        expected.setEmail("email@mail.ru");
    }

    @Test
    void ifUserRowMapperReturnsValidObject() throws SQLException {
        ResultSet resultSet = EasyMock.mock(ResultSet.class);
        EasyMock.expect(resultSet.getLong(TableColumnName.USER_ID)).andReturn(expected.getEntityId());
        EasyMock.expect(resultSet.getString(TableColumnName.EMAIL)).andReturn(expected.getEmail());
        EasyMock.expect(resultSet.getString(TableColumnName.LOGIN)).andReturn(expected.getLogin());
        EasyMock.expect(resultSet.getString(TableColumnName.PASSWORD_HASH)).andReturn(expected.getPasswordHash());
        EasyMock.expect(resultSet.getString(TableColumnName.STATUS)).andReturn(expected.getUserStatus().toString());
        EasyMock.expect(resultSet.getString(TableColumnName.TYPE)).andReturn(expected.getUserRole().toString());
        EasyMock.replay(resultSet);

        RowMapper<User> userRowMapper = new UserRowMapperImpl();
        User actual = userRowMapper.mapRow(resultSet);
        Assertions.assertEquals(actual, expected);
    }
}
