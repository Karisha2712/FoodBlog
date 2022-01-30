package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.RecipePostCategory;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;

public interface RecipePostDao extends Dao<RecipePost> {
    List<RecipePost> findRecipePostsByCategory(RecipePostCategory category) throws DaoException;

    List<RecipePost> findRecipePostsByUserId(Long userId) throws DaoException;

    long updateRecipePostRating(long postId, double rating) throws DaoException;
}
