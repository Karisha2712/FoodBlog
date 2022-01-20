package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.ServiceFactory;
import edu.radyuk.foodblog.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        boolean isAdmin = Boolean.parseBoolean(request.getParameter(RequestParameter.IS_ADMIN));
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String email = request.getParameter(RequestParameter.EMAIL);
        UserService userService = ServiceFactory.getInstance().getUserService();
        UserRole userRole = isAdmin ? UserRole.ADMIN : UserRole.BLOGGER;

        User user;
        try {
            user = userService.signUp(login, password, email, userRole, UserStatus.ACTIVE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return new CommandResponse(PagePath.HOME_PAGE, RoutingType.FORWARD);
    }
}