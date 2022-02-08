package edu.radyuk.foodblog.controller.filter;

import edu.radyuk.foodblog.controller.command.PagePath;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.UserStatus;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static edu.radyuk.foodblog.controller.command.SessionAttribute.USER;

/**
 * The type User status validation filter.
 */
public class UserStatusValidationFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user != null) {
            UserService service = ServiceProvider.getInstance().getUserService();
            Optional<User> optionalUser;
            try {
                optionalUser = service.retrieveUserById(user.getEntityId());
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
            if (optionalUser.isEmpty() || optionalUser.get().getUserStatus() == UserStatus.BLOCKED) {
                session.invalidate();
                response.sendRedirect(request.getContextPath() + PagePath.HOME_PAGE_REDIRECT);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
