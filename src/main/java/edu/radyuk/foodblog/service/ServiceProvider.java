package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.service.impl.*;

/**
 * The type Service provider.
 */
public class ServiceProvider {
    private final UserService userService = new UserServiceImpl();
    private final RecipePostService recipePostService = new RecipePostServiceImpl();
    private final BloggerInfoService bloggerInfoService = new BloggerInfoServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();
    private final PictureLoadingService pictureLoadingService = new PictureLoadingServiceImpl();

    private ServiceProvider() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceProvider getInstance() {
        return Holder.instance;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets recipe post service.
     *
     * @return the recipe post service
     */
    public RecipePostService getRecipePostService() {
        return recipePostService;
    }

    /**
     * Gets blogger info service.
     *
     * @return the blogger info service
     */
    public BloggerInfoService getBloggerInfoService() {
        return bloggerInfoService;
    }

    /**
     * Gets comment service.
     *
     * @return the comment service
     */
    public CommentService getCommentService() {
        return commentService;
    }

    /**
     * Gets picture loading service.
     *
     * @return the picture loading service
     */
    public PictureLoadingService getPictureLoadingService() {
        return pictureLoadingService;
    }

    private static class Holder {
        private static final ServiceProvider instance = new ServiceProvider();
    }
}
