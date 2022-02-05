package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;

/**
 * The interface Recipe post dao.
 */
public interface RecipePostDao extends Dao<RecipePost> {

    /**
     * Find recipe posts by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<RecipePost> findRecipePostsByUserId(Long userId) throws DaoException;

    /**
     * Find recipe posts by dish name list.
     *
     * @param dishName the dish name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<RecipePost> findRecipePostsByDishName(String dishName) throws DaoException;

    /**
     * Update recipe post rating long.
     *
     * @param postId the post id
     * @param rating the rating
     * @return the long
     * @throws DaoException the dao exception
     */
    long updateRecipePostRating(long postId, double rating) throws DaoException;

    /**
     * Update recipe post picture long.
     *
     * @param postId      the post id
     * @param picturePath the picture path
     * @return the long
     * @throws DaoException the dao exception
     */
    long updateRecipePostPicture(long postId, String picturePath) throws DaoException;

    /**
     * Count all recipe posts int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int countAllRecipePosts() throws DaoException;

    /**
     * Count recipe posts with same dish name part int.
     *
     * @param dishName the dish name
     * @return the int
     * @throws DaoException the dao exception
     */
    int countRecipePostsWithSameDishNamePart(String dishName) throws DaoException;

    /**
     * Find recipe posts from range list.
     *
     * @param skipNumber the skip number
     * @param number     the number
     * @return the list
     * @throws DaoException the dao exception
     */
    List<RecipePost> findRecipePostsFromRange(int skipNumber, int number) throws DaoException;

    /**
     * Find recipe posts from range with same dish name parts list.
     *
     * @param dishName   the dish name
     * @param skipNumber the skip number
     * @param number     the number
     * @return the list
     * @throws DaoException the dao exception
     */
    List<RecipePost> findRecipePostsFromRangeWithSameDishNameParts(String dishName,
                                                                   int skipNumber, int number) throws DaoException;
}
