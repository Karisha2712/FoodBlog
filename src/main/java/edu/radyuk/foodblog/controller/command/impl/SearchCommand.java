package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.RecipePostService;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.validator.FormValidator;
import edu.radyuk.foodblog.validator.ValidatorProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

import static edu.radyuk.foodblog.controller.command.MessageKey.EMPTY_POSTS_TABLE;
import static edu.radyuk.foodblog.controller.command.PagePath.*;
import static edu.radyuk.foodblog.controller.command.RequestParameter.*;
import static edu.radyuk.foodblog.controller.command.RoutingType.FORWARD;
import static edu.radyuk.foodblog.controller.command.RoutingType.REDIRECT;

public class SearchCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RecipePostService service = serviceProvider.getRecipePostService();
        String dishName = request.getParameter(SEARCH_VALUE).toUpperCase(Locale.ROOT);
        FormValidator validator = ValidatorProvider.getInstance().getFormValidator();
        if (!validator.areSearchParameterValid(dishName)) {
            logger.log(Level.WARN, "Invalid search value");
            return new CommandResponse(RECIPES_PAGE_REDIRECT, REDIRECT);
        }

        List<RecipePostDto> recipePosts;
        try {
            recipePosts = service.retrieveRecipePostsByDishName(dishName);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, REDIRECT);
        }

        if (recipePosts.isEmpty()) {
            logger.log(Level.INFO, "There are no posts yet");
            request.setAttribute(NO_POSTS, EMPTY_POSTS_TABLE);
        } else {
            request.setAttribute(RECIPE_POSTS, recipePosts);
        }

        return new CommandResponse(RECIPES_PAGE, FORWARD);
    }
}