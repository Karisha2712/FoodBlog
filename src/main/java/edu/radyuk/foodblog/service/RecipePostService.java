package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.RecipePostCategory;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface RecipePostService {
    List<RecipePostDto> retrieveAllRecipePosts() throws ServiceException;

    Optional<RecipePostDto> retrieveRecipePostById(long id) throws ServiceException;

    List<RecipePostDto> retrieveRecipePostsByUserId(long userId) throws ServiceException;

    List<RecipePostDto> retrieveRecipePostsByDishName(String dishName) throws ServiceException;

    List<RecipePostDto> retrieveRecipePostsForPage(int page, String dishName) throws ServiceException;

    int retrievePagesNumber(String dishName) throws ServiceException;

    long addNewRecipePost(String category, String recipeText,
                          String dishName, long userId, List<Part> pictureParts) throws ServiceException;

    long refreshRecipePostPicture(long postId, String picturePath) throws ServiceException;

    long deletePostById(long postId) throws ServiceException;
}
