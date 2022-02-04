package edu.radyuk.foodblog.controller.command.impl.admin;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.RequestParameter;
import edu.radyuk.foodblog.controller.command.RoutingType;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.CommentService;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.validator.IdValidator;
import edu.radyuk.foodblog.validator.ValidatorProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_500_PAGE;
import static edu.radyuk.foodblog.controller.command.PagePath.VIEW_FULL_RECIPE_REDIRECT;

public class DeleteCommentCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String commentIdParameter = request.getParameter(RequestParameter.COMMENT_ID);
        String postIdParameter = request.getParameter(RequestParameter.POST_ID);

        IdValidator idValidator = ValidatorProvider.getInstance().getIdValidator();
        if (!idValidator.isIdPositive(commentIdParameter)
                || !idValidator.isIdPositive(postIdParameter)) {
            logger.log(Level.ERROR, "Invalid id");
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }
        long commentId = Long.parseLong(commentIdParameter);
        long postId = Long.parseLong(postIdParameter);

        CommentService service = ServiceProvider.getInstance().getCommentService();
        try {
            service.deleteCommentById(commentId);
            service.overwriteRecipePostRating(postId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        return new CommandResponse(VIEW_FULL_RECIPE_REDIRECT + postId, RoutingType.REDIRECT);
    }
}
