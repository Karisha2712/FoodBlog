package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.RecipePostCategory;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;

public interface RecipePostDao extends Dao<RecipePost> {
    List<RecipePost> findRecipePostsByCategory(RecipePostCategory category) throws DaoException;

    List<RecipePost> findRecipePostsByUserId(Long userId) throws DaoException;

    List<RecipePost> findRecipePostsByDishName(String dishName) throws DaoException;

    long updateRecipePostRating(long postId, double rating) throws DaoException;

    long updateRecipePostPicture(long postId, String picturePath) throws DaoException;

    int countAllRecipePosts() throws DaoException;

    int countRecipePostsWithSameDishNamePart(String dishName) throws DaoException;

    List<RecipePost> findRecipePostsFromRange(int skipNumber, int number) throws DaoException;

    List<RecipePost> findRecipePostsFromRangeWithSameDishNameParts(String dishName, int skipNumber, int number) throws DaoException;
}
