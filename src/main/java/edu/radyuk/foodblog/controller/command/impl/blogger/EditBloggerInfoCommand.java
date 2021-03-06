package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.RoutingType;
import edu.radyuk.foodblog.controller.command.SessionAttribute;
import edu.radyuk.foodblog.entity.BloggerInfo;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
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

import static edu.radyuk.foodblog.controller.command.MessageKey.INVALID_EDIT_INFO_FORM_INPUT;
import static edu.radyuk.foodblog.controller.command.PagePath.*;
import static edu.radyuk.foodblog.controller.command.RequestParameter.*;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.EDIT_INFO_ERROR;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.USER;

/**
 * The type Edit blogger info command.
 */
public class EditBloggerInfoCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String country = request.getParameter(COUNTRY);
        String city = request.getParameter(CITY);
        String ageString = request.getParameter(AGE);
        String personalInfo = request.getParameter(PERSONAL_INFO);
        List<Part> pictureParts = PicturePartsLoader.loadPictureParts(request, USER_AVATAR);

        FormValidator validator = ValidatorProvider.getInstance().getFormValidator();
        HttpSession session = request.getSession();
        if (pictureParts.isEmpty()) {
            logger.log(Level.ERROR, "Invalid form input");
            session.setAttribute(EDIT_INFO_ERROR, INVALID_EDIT_INFO_FORM_INPUT);
            return new CommandResponse(EDIT_INFO_PAGE, RoutingType.FORWARD);
        }

        String fileName = pictureParts.get(0).getSubmittedFileName();
        if (!validator.isEditInfoParametersValid(city, country, ageString, personalInfo, fileName)) {
            logger.log(Level.ERROR, "Invalid form input");
            session.setAttribute(EDIT_INFO_ERROR, INVALID_EDIT_INFO_FORM_INPUT);
            return new CommandResponse(EDIT_INFO_PAGE, RoutingType.FORWARD);
        }

        int age = Integer.parseInt(ageString);
        User user = (User) session.getAttribute(USER);
        BloggerInfoService service = ServiceProvider.getInstance().getBloggerInfoService();
        BloggerInfo bloggerInfo;
        try {
            bloggerInfo = service.refreshBloggerInfo(city, country, age, personalInfo,
                    user.getEntityId(), pictureParts);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.ERROR);
        }
        session.setAttribute(BLOGGER_INFO, bloggerInfo);
        session.setAttribute(SessionAttribute.USER_AVATAR, bloggerInfo.getAvatarPath());
        return new CommandResponse(PROFILE_PAGE_REDIRECT + user.getEntityId(), RoutingType.REDIRECT);
    }
}
