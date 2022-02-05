package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

/**
 * The interface Recipe post service.
 */
public interface RecipePostService {
    /**
     * Retrieve recipe post by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<RecipePostDto> retrieveRecipePostById(long id) throws ServiceException;

    /**
     * Retrieve recipe posts by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<RecipePostDto> retrieveRecipePostsByUserId(long userId) throws ServiceException;

    /**
     * Retrieve recipe posts for page list.
     *
     * @param page     the page
     * @param dishName the dish name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<RecipePostDto> retrieveRecipePostsForPage(int page, String dishName) throws ServiceException;

    /**
     * Retrieve pages number int.
     *
     * @param dishName the dish name
     * @return the int
     * @throws ServiceException the service exception
     */
    int retrievePagesNumber(String dishName) throws ServiceException;

    /**
     * Add new recipe post long.
     *
     * @param category     the category
     * @param recipeText   the recipe text
     * @param dishName     the dish name
     * @param userId       the user id
     * @param pictureParts the picture parts
     * @return the long
     * @throws ServiceException the service exception
     */
    long addNewRecipePost(String category, String recipeText,
                          String dishName, long userId, List<Part> pictureParts) throws ServiceException;

    /**
     * Refresh recipe post picture long.
     *
     * @param postId      the post id
     * @param picturePath the picture path
     * @return the long
     * @throws ServiceException the service exception
     */
    long refreshRecipePostPicture(long postId, String picturePath) throws ServiceException;

    /**
     * Delete post by id long.
     *
     * @param postId the post id
     * @return the long
     * @throws ServiceException the service exception
     */
    long deletePostById(long postId) throws ServiceException;
}
