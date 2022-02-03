package edu.radyuk.foodblog.controller.filter;

import edu.radyuk.foodblog.controller.command.CommandType;
import edu.radyuk.foodblog.controller.command.RequestParameter;
import edu.radyuk.foodblog.controller.command.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static edu.radyuk.foodblog.controller.command.CommandType.CHANGE_LANGUAGE;

public class SaveCurrentQueryFilter implements Filter {
    private static final String GET_METHOD = "GET";
    private static final String QUESTION_MARK = "?";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (httpServletRequest.getMethod().equals(GET_METHOD)) {
            String contextPath = httpServletRequest.getContextPath();
            String requestURI = httpServletRequest.getRequestURI();
            int startIndex = requestURI.indexOf(contextPath) + contextPath.length();
            String substr = requestURI.substring(startIndex);
            String queryParameters = httpServletRequest.getQueryString();
            String queryLine = queryParameters == null ? substr : substr + QUESTION_MARK + queryParameters;
            String commandString = httpServletRequest.getParameter(RequestParameter.COMMAND);
            CommandType currentCommand = CommandType.getCommandType(commandString);
            if (currentCommand != CHANGE_LANGUAGE) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute(SessionAttribute.PREVIOUS_QUERY, queryLine);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
