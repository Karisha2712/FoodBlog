package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.RecipePostService;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.validator.IdValidator;
import edu.radyuk.foodblog.validator.ValidatorProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_500_PAGE;

/**
 * The type Delete recipe post command.
 */
public class DeleteRecipePostCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String postIdParameter = request.getParameter(RequestParameter.POST_ID);
        IdValidator idValidator = ValidatorProvider.getInstance().getIdValidator();
        if (!idValidator.isIdPositive(postIdParameter)) {
            logger.log(Level.ERROR, "Invalid post id");
            return new CommandResponse(ERROR_500_PAGE, RoutingType.ERROR);
        }
        long postId = Long.parseLong(postIdParameter);

        RecipePostService service = ServiceProvider.getInstance().getRecipePostService();
        try {
            service.deletePostById(postId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.ERROR);
        }
        HttpSession session = request.getSession();
        String previousQuery = session.getAttribute(SessionAttribute.PREVIOUS_QUERY).toString();
        return new CommandResponse(previousQuery, RoutingType.REDIRECT);
    }
}
