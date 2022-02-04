package edu.radyuk.foodblog.controller.command.impl.admin;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.PagePath;
import edu.radyuk.foodblog.controller.command.RoutingType;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.ServiceProvider;
import edu.radyuk.foodblog.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static edu.radyuk.foodblog.controller.command.PagePath.ADMIN_PAGE;
import static edu.radyuk.foodblog.controller.command.RequestParameter.APPROVED_USERS;
import static edu.radyuk.foodblog.controller.command.RequestParameter.UNAPPROVED_USERS;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.USER;

public class GoToAdminPageCommand implements ClientCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        UserService service = ServiceProvider.getInstance().getUserService();
        List<User> approvedUsers;
        List<User> unapprovedUsers;
        try {
            approvedUsers = service.retrieveApprovedUsers();
            unapprovedUsers = service.retrieveUnapprovedUsers();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return new CommandResponse(PagePath.ERROR_500_PAGE, RoutingType.REDIRECT);
        }

        User user = (User) request.getSession().getAttribute(USER);
        approvedUsers.remove(user);

        request.setAttribute(UNAPPROVED_USERS, unapprovedUsers);
        request.setAttribute(APPROVED_USERS, approvedUsers);
        return new CommandResponse(ADMIN_PAGE, RoutingType.FORWARD);
    }
}
