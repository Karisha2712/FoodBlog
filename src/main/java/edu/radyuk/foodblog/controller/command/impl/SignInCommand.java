package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.*;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static edu.radyuk.foodblog.controller.command.MessageKey.INVALID_SIGN_IN_FORM_INPUT;
import static edu.radyuk.foodblog.controller.command.MessageKey.UNAVAILABLE_ACCESS;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.*;

public class SignInCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String password = request.getParameter(RequestParameter.PASSWORD);
        String email = request.getParameter(RequestParameter.EMAIL);

        UserService userService = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession();

        Optional<User> optionalUser;
        try {
            optionalUser = userService.retrieveUserIfExists(password, email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(PagePath.ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        if (optionalUser.isEmpty()) {
            logger.log(Level.WARN, "User with such email does not exist");
            session.setAttribute(SIGN_IN_ERROR, INVALID_SIGN_IN_FORM_INPUT);
            return new CommandResponse(PagePath.SIGN_IN_PAGE_REDIRECT, RoutingType.REDIRECT);
        }

        User user = optionalUser.get();
        if (user.getUserStatus() != UserStatus.ACTIVE) {
            logger.log(Level.WARN, "User is blocked or awaiting_confirmation");
            session.setAttribute(SIGN_IN_ERROR, UNAVAILABLE_ACCESS);
            return new CommandResponse(PagePath.SIGN_IN_PAGE_REDIRECT, RoutingType.REDIRECT);
        }

        session.setAttribute(USER, user);

        switch (user.getUserRole()) {
            case BLOGGER: {
                BloggerInfoService bloggerInfoService = ServiceProvider.getInstance().getBloggerInfoService();
                String userAvatar;
                try {
                    userAvatar = bloggerInfoService.retrievePicturePathByUserLogin(user.getLogin());
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                    return new CommandResponse(PagePath.ERROR_500_PAGE, RoutingType.REDIRECT);
                }
                session.setAttribute(USER_AVATAR, userAvatar);
                return new CommandResponse(PagePath.PROFILE_PAGE_REDIRECT + user.getEntityId(), RoutingType.REDIRECT);
            }
            case ADMIN: {
                return new CommandResponse(PagePath.ADMIN_PAGE_REDIRECT, RoutingType.REDIRECT);
            }
            default: {
                logger.log(Level.ERROR, "User has invalid role");
                return new CommandResponse(PagePath.ERROR_500_PAGE, RoutingType.REDIRECT);
            }
        }
    }
}
