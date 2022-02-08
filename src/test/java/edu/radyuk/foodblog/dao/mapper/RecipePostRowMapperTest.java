package edu.radyuk.foodblog.dao.mapper;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.impl.RecipePostRowMapperImpl;
import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.RecipePostCategory;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

class RecipePostRowMapperTest {
    private static RecipePost expected;

    @BeforeAll
    static void init() {
        expected = new RecipePost();
        expected.setUserId(1);
        expected.setEntityId(1);
        expected.setPicturePath("1.png");
        expected.setPostDate(LocalDateTime.now());
        expected.setRecipeText("Recipe text");
        expected.setDishName("Dish name");
        expected.setPostRating(0.0);
        expected.setRecipePostCategory(RecipePostCategory.APPETIZER);
    }

    @Test
    void ifRecipePostRowMapperReturnsValidObject() throws SQLException {
        ResultSet resultSet = EasyMock.mock(ResultSet.class);
        EasyMock.expect(resultSet.getLong(TableColumnName.POST_ID)).andReturn(expected.getEntityId());
        EasyMock.expect(resultSet.getLong(TableColumnName.USERS_USER_ID)).andReturn(expected.getUserId());
        EasyMock.expect(resultSet.getString(TableColumnName.POST_TEXT)).andReturn(expected.getRecipeText());
        EasyMock.expect(resultSet.getString(TableColumnName.POST_PICTURE)).andReturn(expected.getPicturePath());
        EasyMock.expect(resultSet.getDouble(TableColumnName.RATING)).andReturn(expected.getPostRating());
        EasyMock.expect(resultSet.getString(TableColumnName.DISH_NAME)).andReturn(expected.getDishName());
        EasyMock.expect(resultSet.getString(TableColumnName.CATEGORY)).andReturn(expected.getRecipePostCategory().toString());
        EasyMock.expect(resultSet.getTimestamp(TableColumnName.COMMENT_DATE))
                .andReturn(Timestamp.valueOf(expected.getPostDate()));
        EasyMock.replay(resultSet);

        RowMapper<RecipePost> recipePostRowMapper = new RecipePostRowMapperImpl();
        RecipePost actual = recipePostRowMapper.mapRow(resultSet);
        Assertions.assertEquals(actual, expected);
    }
}
