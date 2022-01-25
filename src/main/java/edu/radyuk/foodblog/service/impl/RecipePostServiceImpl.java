package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.dao.RecipePostDao;
import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.RecipePostService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipePostServiceImpl implements RecipePostService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<RecipePostDto> retrieveRecipePosts() throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            List<RecipePost> recipePosts = recipePostDao.findAll();
            List<RecipePostDto> resultRecipePosts = new ArrayList<>(recipePosts.size());
            for (RecipePost recipePost : recipePosts) {
                Optional<User> optionalUser = userDao.findEntityById(recipePost.getUserId());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    RecipePostDto recipePostDto = new RecipePostDto();
                    recipePostDto.setPostDate(recipePost.getPostDate());
                    recipePostDto.setDishName(recipePost.getDishName());
                    recipePostDto.setRecipePostCategory(recipePost.getRecipePostCategory());
                    recipePostDto.setRecipeText(recipePost.getRecipeText());
                    recipePostDto.setPostRating(recipePost.getPostRating());
                    recipePostDto.setPicturePath(recipePost.getPicturePath());
                    recipePostDto.setUserId(user.getEntityId());
                    recipePostDto.setUserLogin(user.getLogin());
                    resultRecipePosts.add(recipePostDto);
                    //TODO add user picture
                }
            }
            return resultRecipePosts;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }
}
