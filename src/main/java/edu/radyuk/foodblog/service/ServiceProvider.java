package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.service.impl.RecipePostServiceImpl;
import edu.radyuk.foodblog.service.impl.UserServiceImpl;

public class ServiceProvider {
    private final UserService userService = new UserServiceImpl();
    private final RecipePostService recipePostService = new RecipePostServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return Holder.instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public RecipePostService getRecipePostService() {
        return recipePostService;
    }

    private static class Holder {
        private static final ServiceProvider instance = new ServiceProvider();
    }
}
