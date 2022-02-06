package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.RoutingType;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.RecipePostService;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.validator.FormValidator;
import edu.radyuk.foodblog.validator.ValidatorProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;

import static edu.radyuk.foodblog.controller.command.MessageKey.INVALID_ADD_POST_FORM_INPUT;
import static edu.radyuk.foodblog.controller.command.PagePath.*;
import static edu.radyuk.foodblog.controller.command.RequestParameter.*;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.ADD_POST_ERROR;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.USER;

/**
 * The type Add recipe post command.
 */
public class AddRecipePostCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String dishName = request.getParameter(DISH_NAME);
        String recipeCategory = request.getParameter(RECIPE_CATEGORY);
        String recipeText = request.getParameter(RECIPE_TEXT);
        List<Part> pictureParts = PicturePartsLoader.loadPictureParts(request, POST_PICTURE);

        FormValidator validator = ValidatorProvider.getInstance().getFormValidator();
        HttpSession session = request.getSession();
        if (pictureParts.isEmpty()) {
            logger.log(Level.ERROR, "Invalid form input");
            session.setAttribute(ADD_POST_ERROR, INVALID_ADD_POST_FORM_INPUT);
            return new CommandResponse(ADD_RECIPE_POST_PAGE, RoutingType.FORWARD);
        }

        String fileName = pictureParts.get(0).getSubmittedFileName();
        if (!validator.isRecipePostParametersValid(dishName, recipeCategory, fileName, recipeText)) {
            logger.log(Level.ERROR, "Invalid form input");
            session.setAttribute(ADD_POST_ERROR, INVALID_ADD_POST_FORM_INPUT);
            return new CommandResponse(ADD_RECIPE_POST_PAGE, RoutingType.FORWARD);
        }

        User user = (User) session.getAttribute(USER);
        RecipePostService service = ServiceProvider.getInstance().getRecipePostService();

        try {
            service.addNewRecipePost(recipeCategory, recipeText, dishName, user.getEntityId(), pictureParts);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }
        return new CommandResponse(PROFILE_PAGE_REDIRECT + user.getEntityId(), RoutingType.REDIRECT);
    }
}
