package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;

import java.util.List;

public interface RecipePostService {
    List<RecipePostDto> retrieveRecipePosts() throws ServiceException;
}
