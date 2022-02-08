package edu.radyuk.foodblog.controller.filter;

import edu.radyuk.foodblog.controller.command.CommandType;
import edu.radyuk.foodblog.controller.command.RequestParameter;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

import static edu.radyuk.foodblog.controller.command.CommandType.*;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.USER;

/**
 * The type Command access filter.
 */
public class CommandAccessFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private EnumSet<CommandType> guestAvailableCommands;
    private EnumSet<CommandType> bloggerAvailableCommands;
    private EnumSet<CommandType> adminAvailableCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestAvailableCommands = EnumSet.of(INVALID, GO_TO_SIGN_IN_PAGE, GO_TO_SIGN_UP_PAGE,
                GO_TO_HOME_PAGE, GO_TO_ABOUT_PAGE, GO_TO_RECIPES_PAGE, VIEW_FULL_RECIPE,
                CHANGE_LANGUAGE, GO_TO_PROFILE_PAGE, SIGN_IN, SIGN_UP);
        bloggerAvailableCommands = EnumSet.of(INVALID, GO_TO_SIGN_IN_PAGE, GO_TO_SIGN_UP_PAGE,
                GO_TO_HOME_PAGE, GO_TO_ABOUT_PAGE, GO_TO_RECIPES_PAGE, VIEW_FULL_RECIPE,
                CHANGE_LANGUAGE, GO_TO_PROFILE_PAGE, GO_TO_EDIT_BLOGGER_INFO, GO_TO_ADD_NEW_POST,
                EDIT_BLOGGER_INFO, ADD_RECIPE_POST, DELETE_COMMENT, DELETE_POST, COMMENT, LOGOUT);
        adminAvailableCommands = EnumSet.of(INVALID, GO_TO_SIGN_IN_PAGE, GO_TO_SIGN_UP_PAGE,
                GO_TO_HOME_PAGE, GO_TO_ABOUT_PAGE, GO_TO_RECIPES_PAGE, VIEW_FULL_RECIPE,
                CHANGE_LANGUAGE, GO_TO_PROFILE_PAGE, DELETE_COMMENT, DELETE_POST, GO_TO_ADMIN_PAGE,
                CHANGE_USER_STATUS, LOGOUT);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String commandString = request.getParameter(RequestParameter.COMMAND);
        CommandType currentCommand = CommandType.getCommandType(commandString);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        UserRole role;

        if (user == null) {
            role = UserRole.UNAUTHORISED;
        } else {
            role = user.getUserRole();
        }

        EnumSet<CommandType> availableCommands;

        switch (role) {
            case UNAUTHORISED: {
                availableCommands = guestAvailableCommands;
                break;
            }
            case BLOGGER: {
                availableCommands = bloggerAvailableCommands;
                break;
            }
            case ADMIN: {
                availableCommands = adminAvailableCommands;
                break;
            }
            default: {
                logger.log(Level.ERROR, "Invalid user role");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        }

        if (!availableCommands.contains(currentCommand)) {
            logger.log(Level.ERROR, "Unavailable command");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
