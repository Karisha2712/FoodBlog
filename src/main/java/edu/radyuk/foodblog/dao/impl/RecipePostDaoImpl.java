package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.JdbcHelper;
import edu.radyuk.foodblog.dao.RecipePostDao;
import edu.radyuk.foodblog.dao.mapper.impl.RecipePostRowMapperImpl;
import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.RecipePostCategory;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class RecipePostDaoImpl implements RecipePostDao {
    private static final String FIND_ALL_POSTS_QUERY = "SELECT * FROM posts " +
            "JOIN post_categories " +
            "ON post_categories_category_id = category_id";
    private static final String FIND_POSTS_BY_CATEGORY = FIND_ALL_POSTS_QUERY
            + " WHERE category_id = " +
            "(SELECT category_id FROM categories WHERE category = ?)";
    private static final String FIND_POSTS_BY_USER_ID = FIND_ALL_POSTS_QUERY
            + " WHERE users_user_id = ? ORDER BY date";
    private static final String FIND_POST_BY_ID = FIND_ALL_POSTS_QUERY
            + " WHERE post_id = ?";
    private static final String INSERT_NEW_POST_QUERY = "INSERT INTO posts " +
            "(post_text, picture, date, rating, users_user_id, dish_name, post_categories_category_id) " +
            "VALUES(?, ?, ?, ?, ?, ? " +
            "(SELECT category_id FROM categories WHERE category = ?))";
    private static final String UPDATE_RATING_QUERY = "UPDATE posts SET rating = ? where post_id = ?";
    private static final String FIND_POST_BY_DISH_NAME_QUERY = FIND_ALL_POSTS_QUERY +
            " WHERE dish_name = ?";

    private JdbcHelper<RecipePost> jdbcHelper;

    public RecipePostDaoImpl() {
        jdbcHelper = new JdbcHelper<>(ConnectionPool.getInstance(), new RecipePostRowMapperImpl());
    }

    @Override
    public List<RecipePost> findAll() throws DaoException {
        return jdbcHelper.executeQuery(FIND_ALL_POSTS_QUERY);
    }

    @Override
    public Optional<RecipePost> findEntityById(long id) throws DaoException {
        return jdbcHelper.executeQueryForSingleObject(FIND_POST_BY_ID, id);
    }

    @Override
    public long insert(RecipePost recipePost) throws DaoException {
        return jdbcHelper.executeUpdate(INSERT_NEW_POST_QUERY,
                recipePost.getRecipeText(),
                recipePost.getPicturePath(),
                recipePost.getPostDate(),
                recipePost.getPostRating(),
                recipePost.getUserId(),
                recipePost.getDishName(),
                recipePost.getRecipePostCategory());
    }

    @Override
    public long update(RecipePost entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long remove(RecipePost entity) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public long removeEntityById(long id) throws DaoException {
        return 0;
        //TODO
    }

    @Override
    public List<RecipePost> findRecipePostsByCategory(RecipePostCategory category) throws DaoException {
        return jdbcHelper.executeQuery(FIND_POSTS_BY_CATEGORY, category);
    }

    @Override
    public List<RecipePost> findRecipePostsByUserId(Long userId) throws DaoException {
        return jdbcHelper.executeQuery(FIND_POSTS_BY_USER_ID, userId);
    }

    @Override
    public List<RecipePost> findRecipePostsByDishName(String dishName) throws DaoException {
        return jdbcHelper.executeQuery(FIND_POST_BY_DISH_NAME_QUERY, dishName);
    }

    @Override
    public long updateRecipePostRating(long postId, double rating) throws DaoException {
        return jdbcHelper.executeUpdate(UPDATE_RATING_QUERY, rating, postId);
    }
}
