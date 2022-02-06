package edu.radyuk.foodblog.controller.command.impl.admin;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.RequestParameter;
import edu.radyuk.foodblog.controller.command.RoutingType;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.service.UserService;
import edu.radyuk.foodblog.validator.IdValidator;
import edu.radyuk.foodblog.validator.ValidatorProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.*;

/**
 * The type Change user status command.
 */
public class ChangeUserStatusCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String userIdParameter = request.getParameter(RequestParameter.USER_ID);
        String userStatusParameter = request.getParameter(RequestParameter.USER_STATUS);
        IdValidator idValidator = ValidatorProvider.getInstance().getIdValidator();
        if (!idValidator.isIdPositive(userIdParameter)) {
            logger.log(Level.ERROR, "Invalid user id");
            return new CommandResponse(ERROR_404_PAGE, RoutingType.REDIRECT);
        }
        long userId = Long.parseLong(userIdParameter);

        UserStatus userStatus;
        try {
            userStatus = UserStatus.valueOf(userStatusParameter);
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        userStatus = userStatus == UserStatus.ACTIVE ? UserStatus.BLOCKED : UserStatus.ACTIVE;

        UserService service = ServiceProvider.getInstance().getUserService();
        try {
            service.refreshUserStatus(userId, userStatus);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        return new CommandResponse(ADMIN_PAGE_REDIRECT, RoutingType.REDIRECT);
    }
}
