package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.service.UserService;
import edu.radyuk.foodblog.validator.FormValidator;
import edu.radyuk.foodblog.validator.impl.FormValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.radyuk.foodblog.controller.command.MessageKey.*;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.SIGN_UP_ERROR;

public class SignUpCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        boolean isAdmin = Boolean.parseBoolean(request.getParameter(RequestParameter.IS_ADMIN));
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String email = request.getParameter(RequestParameter.EMAIL);
        UserRole userRole = isAdmin ? UserRole.ADMIN : UserRole.BLOGGER;

        UserService userService = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession();
        FormValidator validator = new FormValidatorImpl();

        if (!validator.areSignUpParametersValid(login, email, password)) {
            logger.log(Level.WARN, "Invalid form input");
            session.setAttribute(SIGN_UP_ERROR, INVALID_SIGNUP_FORM_INPUT);
            return new CommandResponse(PagePath.SIGN_UP_PAGE_REDIRECT, RoutingType.REDIRECT);
        }

        boolean isEmailAvailable;
        boolean isLoginAvailable;

        try {
            isEmailAvailable = userService.isEmailAvailable(email);
            isLoginAvailable = userService.isLoginAvailable(login);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(PagePath.ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        if (!isEmailAvailable) {
            logger.log(Level.WARN, "Unavailable email");
            session.setAttribute(SIGN_UP_ERROR, UNAVAILABLE_EMAIL);
            return new CommandResponse(PagePath.SIGN_UP_PAGE_REDIRECT, RoutingType.REDIRECT);
        }

        if (!isLoginAvailable) {
            logger.log(Level.WARN, "Unavailable login");
            session.setAttribute(SIGN_UP_ERROR, UNAVAILABLE_LOGIN);
            return new CommandResponse(PagePath.SIGN_UP_PAGE_REDIRECT, RoutingType.REDIRECT);
        }

        User user;
        try {
            user = userService.signUp(login, password, email, userRole, UserStatus.ACTIVE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(PagePath.ERROR_500_PAGE, RoutingType.REDIRECT);
        }
        return new CommandResponse(PagePath.HOME_PAGE, RoutingType.FORWARD);
        //TODO change page
    }
}