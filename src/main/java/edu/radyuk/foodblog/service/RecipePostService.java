package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.RecipePost;
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

    long addNewRecipePost(RecipePost recipePost) throws ServiceException;

    long refreshRecipePostPicture(long postId, String picturePath) throws ServiceException;

    String saveRecipePostPicture(long postId, List<Part> pictureParts) throws ServiceException;

    long deletePostById(long postId) throws ServiceException;
}
