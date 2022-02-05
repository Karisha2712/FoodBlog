package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.CommentService;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.validator.CommentTextValidator;
import edu.radyuk.foodblog.validator.IdValidator;
import edu.radyuk.foodblog.validator.ValidatorProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import static edu.radyuk.foodblog.controller.command.MessageKey.INVALID_COMMENT;
import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_500_PAGE;
import static edu.radyuk.foodblog.controller.command.PagePath.VIEW_FULL_RECIPE_REDIRECT;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.COMMENT_ERROR;

/**
 * The type Comment command.
 */
public class CommentCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String userIdParameter = request.getParameter(RequestParameter.USER_ID);
        String postIdParameter = request.getParameter(RequestParameter.POST_ID);
        String commentText = request.getParameter(RequestParameter.COMMENT_TEXT);
        String markText = request.getParameter(RequestParameter.MARK);
        IdValidator idValidator = ValidatorProvider.getInstance().getIdValidator();
        CommentTextValidator commentTextValidator = ValidatorProvider.getInstance().getCommentTextValidator();
        HttpSession session = request.getSession();

        if (!idValidator.isIdPositive(userIdParameter)) {
            logger.log(Level.ERROR, "Invalid user id");
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        if (!idValidator.isIdPositive(postIdParameter)) {
            logger.log(Level.ERROR, "Invalid post id");
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        long userId = Long.parseLong(userIdParameter);
        long postId = Long.parseLong(postIdParameter);
        double mark;
        try {
            mark = Double.parseDouble(markText);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Invalid mark", e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        if (!commentTextValidator.isCommentValid(commentText)) {
            logger.log(Level.WARN, "Invalid form input");
            session.setAttribute(COMMENT_ERROR, INVALID_COMMENT);
            return new CommandResponse(PagePath.VIEW_FULL_RECIPE_REDIRECT + postId, RoutingType.REDIRECT);
        }

        LocalDateTime dateTime = LocalDateTime.now();
        CommentService commentService = ServiceProvider.getInstance().getCommentService();
        try {
            commentService.addComment(commentText, dateTime, userId, postId, mark);
            commentService.overwriteRecipePostRating(postId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        return new CommandResponse(VIEW_FULL_RECIPE_REDIRECT + postId, RoutingType.REDIRECT);
    }
}
