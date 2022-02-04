package edu.radyuk.foodblog.controller.filter;

import edu.radyuk.foodblog.controller.command.CommandType;
import edu.radyuk.foodblog.controller.command.RequestParameter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumMap;

import static edu.radyuk.foodblog.controller.command.SessionAttribute.*;

public class SessionValidationFilter implements Filter {
    private EnumMap<CommandType, String> attributesForRemove;

    @Override
    public void init(FilterConfig filterConfig) {
        attributesForRemove = new EnumMap<>(CommandType.class);
        attributesForRemove.put(CommandType.GO_TO_SIGN_UP_PAGE, SIGN_UP_ERROR);
        attributesForRemove.put(CommandType.GO_TO_SIGN_IN_PAGE, SIGN_IN_ERROR);
        attributesForRemove.put(CommandType.GO_TO_EDIT_BLOGGER_INFO, EDIT_INFO_ERROR);
        attributesForRemove.put(CommandType.GO_TO_ADD_NEW_POST, ADD_POST_ERROR);
        attributesForRemove.put(CommandType.VIEW_FULL_RECIPE, COMMENT_ERROR);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String commandString = httpServletRequest.getParameter(RequestParameter.COMMAND);
        CommandType currentCommand = CommandType.getCommandType(commandString);
        HttpSession session = httpServletRequest.getSession();
        attributesForRemove.forEach((command, attribute) -> {
            if (currentCommand != command) {
                session.removeAttribute(attribute);
            }
        });
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
