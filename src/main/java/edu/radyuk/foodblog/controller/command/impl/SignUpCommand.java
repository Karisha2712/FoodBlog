package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.ServiceFactory;
import edu.radyuk.foodblog.service.UserService;
import edu.radyuk.foodblog.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.radyuk.foodblog.controller.command.MessageKey.INVALID_SIGNUP_FORM_INPUT;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.SIGN_UP_ERROR;

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
        HttpSession session = request.getSession();

        if (!FormValidator.areSignUpParametersValid(login, email, password)) {
            logger.log(Level.WARN, "Invalid form input");
            session.setAttribute(SIGN_UP_ERROR, INVALID_SIGNUP_FORM_INPUT);
            return new CommandResponse(PagePath.SIGN_UP_PAGE_REDIRECT, RoutingType.REDIRECT);
        }

        User user;
        try {
            user = userService.signUp(login, password, email, userRole, UserStatus.ACTIVE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return new CommandResponse(PagePath.HOME_PAGE, RoutingType.FORWARD);
        //TODO change page
    }
}