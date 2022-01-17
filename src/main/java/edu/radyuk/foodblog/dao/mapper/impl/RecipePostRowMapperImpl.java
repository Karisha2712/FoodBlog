package edu.radyuk.foodblog.dao.mapper.impl;

import edu.radyuk.foodblog.dao.TableColumnName;
import edu.radyuk.foodblog.dao.mapper.RowMapper;
import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.RecipePostCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;

public class RecipePostRowMapperImpl implements RowMapper<RecipePost> {
    @Override
    public RecipePost mapRow(ResultSet resultSet) throws SQLException {
        RecipePost recipePost = new RecipePost();
        recipePost.setEntityId(resultSet.getLong(TableColumnName.POST_ID));
        recipePost.setUserId(resultSet.getLong(TableColumnName.USER_ID));
        recipePost.setRecipeText(resultSet.getString(TableColumnName.POST_TEXT));
        recipePost.setPicturePath(resultSet.getString(TableColumnName.POST_PICTURE));
        recipePost.setPostRating(resultSet.getDouble(TableColumnName.RATING));
        String category = resultSet.getString(TableColumnName.CATEGORY.toUpperCase(Locale.ROOT));
        recipePost.setRecipePostCategory(RecipePostCategory.valueOf(category));
        LocalDateTime dateTime = resultSet.getTimestamp(TableColumnName.POST_DATE).toLocalDateTime();
        recipePost.setPostDate(dateTime);
        return recipePost;
    }
}
