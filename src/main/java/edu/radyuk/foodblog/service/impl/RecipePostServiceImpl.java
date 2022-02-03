package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.dao.RecipePostDao;
import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.entity.RecipePost;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import edu.radyuk.foodblog.service.RecipePostService;
import edu.radyuk.foodblog.service.ServiceProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class RecipePostServiceImpl implements RecipePostService {
    private static final Logger logger = LogManager.getLogger();
    private static final String PICTURE_PROPERTIES = "property/picture.properties";
    private static final String PICTURE_PATH_PROPERTY = "picture.post";
    private static final String BASE_DIRECTORY_PROPERTY = "picture.base_directory";
    private static final String DEFAULT_BASE_DIRECTORY = "D:/blog_pictures/";
    private static final String DEFAULT_PICTURE_PATH_PROPERTY = "posts/";
    private static final String EXTENSION = ".png";
    private static final int POST_NUMBER_PER_PAGE = 3;
    private String baseDirectory;
    private String recipePostPicturePath;

    public RecipePostServiceImpl() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Properties properties = new Properties();
            properties.load(classLoader.getResourceAsStream(PICTURE_PROPERTIES));
            baseDirectory = properties.getProperty(BASE_DIRECTORY_PROPERTY);
            recipePostPicturePath = properties.getProperty(PICTURE_PATH_PROPERTY);
            if (baseDirectory == null || recipePostPicturePath == null) {
                baseDirectory = DEFAULT_BASE_DIRECTORY;
                recipePostPicturePath = DEFAULT_PICTURE_PATH_PROPERTY;
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading property file {} ", PICTURE_PROPERTIES, e);
            baseDirectory = DEFAULT_BASE_DIRECTORY;
            recipePostPicturePath = DEFAULT_PICTURE_PATH_PROPERTY;
        }
    }

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
    public long addNewRecipePost(RecipePost recipePost) throws ServiceException {
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        try {
            return recipePostDao.insert(recipePost);
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
    public String saveRecipePostPicture(long postId, List<Part> pictureParts) throws ServiceException {
        String relativePicturePath = recipePostPicturePath + postId + EXTENSION;  //TODO remove duplicate code
        String absolutePath = baseDirectory + relativePicturePath;
        try (FileOutputStream fileOutputStream = new FileOutputStream(absolutePath)) {
            for (Part part : pictureParts) {
                part.getInputStream().transferTo(fileOutputStream);
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return relativePicturePath;
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
        String userAvatar = bloggerInfoService.retrievePicturePathByUserLogin(user.getLogin());
        recipePostDto.setUserPicturePath(userAvatar);
        recipePostDto.setPostId(recipePost.getEntityId());
        return recipePostDto;
    }
}
