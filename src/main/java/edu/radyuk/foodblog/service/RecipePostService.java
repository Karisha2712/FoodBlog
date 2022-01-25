package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface RecipePostService {
    List<RecipePostDto> retrieveRecipePosts() throws ServiceException;

    Optional<RecipePostDto> retrieveRecipePostById(long id) throws ServiceException;
}
