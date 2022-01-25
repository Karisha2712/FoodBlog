package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.entity.dto.CommentDto;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.CommentService;
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

import static edu.radyuk.foodblog.controller.command.MessageKey.NO_COMMENTS_FOR_POST;
import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_404_PAGE;
import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_500_PAGE;
import static edu.radyuk.foodblog.controller.command.RequestParameter.*;

public class ViewFullRecipeCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String postIdParameter = request.getParameter(RequestParameter.POST_ID);
        IdValidator idValidator = ValidatorProvider.getInstance().getIdValidator();
        if (!idValidator.isIdPositive(postIdParameter)) {
            logger.log(Level.ERROR, "Invalid post id");
            return new CommandResponse(ERROR_404_PAGE, RoutingType.REDIRECT);
        }
        long postId = Long.parseLong(postIdParameter);
        RecipePostService recipePostService = ServiceProvider.getInstance().getRecipePostService();
        Optional<RecipePostDto> optionalRecipePostDto;
        try {
            optionalRecipePostDto = recipePostService.retrieveRecipePostById(postId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        if (optionalRecipePostDto.isEmpty()) {
            logger.log(Level.ERROR, "There is no post with id {}", postId);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        CommentService commentService = ServiceProvider.getInstance().getCommentService();
        List<CommentDto> comments;
        try {
            comments = commentService.retrievePostComments(postId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        if (comments.isEmpty()) {
            logger.log(Level.WARN, "There is no comments for this post {}", postId);
            request.setAttribute(NO_COMMENTS, NO_COMMENTS_FOR_POST);
        }

        request.setAttribute(RECIPE_POST, optionalRecipePostDto.get());
        request.setAttribute(COMMENTS, comments);
        return new CommandResponse(PagePath.SINGLE_RECIPE_PAGE, RoutingType.FORWARD);
    }
}
