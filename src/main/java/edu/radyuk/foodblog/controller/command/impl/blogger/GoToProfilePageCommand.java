package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.entity.dto.BloggerInfoDto;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import edu.radyuk.foodblog.service.RecipePostService;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.validator.IdValidator;
import edu.radyuk.foodblog.validator.ValidatorProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static edu.radyuk.foodblog.controller.command.MessageKey.EMPTY_POSTS_TABLE;
import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_404_PAGE;
import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_500_PAGE;
import static edu.radyuk.foodblog.controller.command.RequestParameter.*;

/**
 * The type Go to profile page command.
 */
public class GoToProfilePageCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String userIdParameter = request.getParameter(RequestParameter.USER_ID);
        IdValidator idValidator = ValidatorProvider.getInstance().getIdValidator();
        if (!idValidator.isIdPositive(userIdParameter)) {
            logger.log(Level.ERROR, "Invalid user id");
            return new CommandResponse(ERROR_404_PAGE, RoutingType.ERROR);
        }
        long userId = Long.parseLong(userIdParameter);
        RecipePostService recipePostService = ServiceProvider.getInstance().getRecipePostService();
        List<RecipePostDto> recipePosts;
        try {
            recipePosts = recipePostService.retrieveRecipePostsByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.ERROR);
        }

        if (recipePosts.isEmpty()) {
            logger.log(Level.ERROR, "There are no posts");
            request.setAttribute(NO_POSTS, EMPTY_POSTS_TABLE);
        }
        request.setAttribute(USER_RECIPE_POSTS, recipePosts);

        BloggerInfoService bloggerInfoService = ServiceProvider.getInstance().getBloggerInfoService();
        Optional<BloggerInfoDto> optionalBloggerInfo;
        try {
            optionalBloggerInfo = bloggerInfoService.retrieveBloggerInfoByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(PagePath.ERROR_500_PAGE, RoutingType.ERROR);
        }

        if (optionalBloggerInfo.isEmpty()) {
            logger.log(Level.ERROR, "Invalid user");
            return new CommandResponse(ERROR_404_PAGE, RoutingType.ERROR);
        }

        request.setAttribute(BLOGGER_INFO, optionalBloggerInfo.get());

        return new CommandResponse(PagePath.PROFILE_PAGE, RoutingType.FORWARD);
    }
}
