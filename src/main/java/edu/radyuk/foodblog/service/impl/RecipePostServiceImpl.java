package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.dao.RecipePostDao;
import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.RecipePostCategory;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import edu.radyuk.foodblog.service.PictureLoadingService;
import edu.radyuk.foodblog.service.RecipePostService;
import edu.radyuk.foodblog.service.ServiceProvider;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipePostServiceImpl implements RecipePostService {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_AVATAR = "default_avatar_big.png";
    private static final double DEFAULT_RATING = 0;
    private static final int POST_NUMBER_PER_PAGE = 3;

    @Override
    public List<RecipePostDto> retrieveAllRecipePosts() throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            List<RecipePost> recipePosts = recipePostDao.findAll();
            List<RecipePostDto> resultRecipePosts = new ArrayList<>(recipePosts.size());
            for (RecipePost recipePost : recipePosts) {
                Optional<User> optionalUser = userDao.findEntityById(recipePost.getUserId());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    RecipePostDto recipePostDto = retrieveRecipePostDto(recipePost, user);
                    resultRecipePosts.add(recipePostDto);
                }
            }
            return resultRecipePosts;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<RecipePostDto> retrieveRecipePostById(long id) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            Optional<RecipePost> optionalRecipePost = recipePostDao.findEntityById(id);
            if (optionalRecipePost.isEmpty()) {
                return Optional.empty();
            }
            RecipePost recipePost = optionalRecipePost.get();
            Optional<User> optionalUser = userDao.findEntityById(recipePost.getUserId());
            if (optionalUser.isEmpty()) {
                return Optional.empty();
            }
            User user = optionalUser.get();
            return Optional.of(retrieveRecipePostDto(recipePost, user));

        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RecipePostDto> retrieveRecipePostsByUserId(long userId) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            List<RecipePost> recipePosts = recipePostDao.findRecipePostsByUserId(userId);
            List<RecipePostDto> resultRecipePosts = new ArrayList<>(recipePosts.size());
            Optional<User> optionalUser = userDao.findEntityById(userId);
            if (optionalUser.isEmpty()) {
                logger.log(Level.ERROR, "User with id {} does not exist", userId);
                throw new ServiceException("User with id " + userId + " does not exist");
            }
            for (RecipePost recipePost : recipePosts) {
                User user = optionalUser.get();
                RecipePostDto recipePostDto = retrieveRecipePostDto(recipePost, user);
                resultRecipePosts.add(recipePostDto);
            }
            return resultRecipePosts;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RecipePostDto> retrieveRecipePostsByDishName(String dishName) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();   //TODO remove duplicate code
        try {
            List<RecipePost> recipePosts = recipePostDao.findRecipePostsByDishName(dishName);
            List<RecipePostDto> resultRecipePosts = new ArrayList<>(recipePosts.size());
            for (RecipePost recipePost : recipePosts) {
                Optional<User> optionalUser = userDao.findEntityById(recipePost.getUserId());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    RecipePostDto recipePostDto = retrieveRecipePostDto(recipePost, user);
                    resultRecipePosts.add(recipePostDto);
                }
            }
            return resultRecipePosts;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RecipePostDto> retrieveRecipePostsForPage(int page, String dishName) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();   //TODO remove duplicate code
        int skipNumber = (page - 1) * POST_NUMBER_PER_PAGE;
        try {
            List<RecipePost> recipePosts;
            if (dishName != null) {
                recipePosts = recipePostDao.findRecipePostsFromRangeWithSameDishNameParts(dishName, skipNumber, POST_NUMBER_PER_PAGE);
            } else {
                recipePosts = recipePostDao.findRecipePostsFromRange(skipNumber, POST_NUMBER_PER_PAGE);
            }
            List<RecipePostDto> resultRecipePosts = new ArrayList<>(recipePosts.size());
            for (RecipePost recipePost : recipePosts) {
                Optional<User> optionalUser = userDao.findEntityById(recipePost.getUserId());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    RecipePostDto recipePostDto = retrieveRecipePostDto(recipePost, user);
                    resultRecipePosts.add(recipePostDto);
                }
            }
            return resultRecipePosts;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int retrievePagesNumber(String dishName) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        double pagesNumber;
        try {
            if (dishName != null) {
                pagesNumber = (double) recipePostDao.countRecipePostsWithSameDishNamePart(dishName) / POST_NUMBER_PER_PAGE;
                return (int) Math.ceil(pagesNumber);
            }
            pagesNumber = (double) recipePostDao.countAllRecipePosts() / POST_NUMBER_PER_PAGE;
            return (int) Math.ceil(pagesNumber);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long addNewRecipePost(String category, String recipeText,
                                 String dishName, long userId, List<Part> pictureParts) throws ServiceException {
        RecipePost recipePost = new RecipePost();
        recipePost.setRecipePostCategory(RecipePostCategory.valueOf(category));
        recipeText = StringEscapeUtils.escapeHtml4(recipeText);
        recipePost.setRecipeText(recipeText);
        recipePost.setDishName(dishName);
        recipePost.setUserId(userId);
        recipePost.setPostRating(DEFAULT_RATING);
        recipePost.setPicturePath(DEFAULT_AVATAR);
        recipePost.setPostDate(LocalDateTime.now());
        PictureLoadingService pictureLoadingService = ServiceProvider.getInstance().getPictureLoadingService();
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        try {
            long postId = recipePostDao.insert(recipePost);
            String picturePath = pictureLoadingService.savePicture(postId,
                    PictureLoadingServiceImpl.PictureCategory.POST, pictureParts);
            return refreshRecipePostPicture(postId, picturePath);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long refreshRecipePostPicture(long postId, String picturePath) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        try {
            return recipePostDao.updateRecipePostPicture(postId, picturePath);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long deletePostById(long postId) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        try {
            return recipePostDao.removeEntityById(postId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }


    private RecipePostDto retrieveRecipePostDto(RecipePost recipePost, User user) throws ServiceException {
        BloggerInfoService bloggerInfoService = ServiceProvider.getInstance().getBloggerInfoService();
        RecipePostDto recipePostDto = new RecipePostDto();
        recipePostDto.setPostDate(recipePost.getPostDate());
        recipePostDto.setDishName(recipePost.getDishName());
        recipePostDto.setRecipePostCategory(recipePost.getRecipePostCategory());
        recipePostDto.setRecipeText(recipePost.getRecipeText());
        recipePostDto.setPostRating(recipePost.getPostRating());
        recipePostDto.setPicturePath(recipePost.getPicturePath());
        recipePostDto.setUserId(user.getEntityId());
        recipePostDto.setUserLogin(user.getLogin());
        String userAvatar = bloggerInfoService.retrievePicturePathByUserId(user.getEntityId());
        recipePostDto.setUserPicturePath(userAvatar);
        recipePostDto.setPostId(recipePost.getEntityId());
        return recipePostDto;
    }
}
