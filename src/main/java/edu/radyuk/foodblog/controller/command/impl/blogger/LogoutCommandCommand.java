package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.RoutingType;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.HOME_PAGE_REDIRECT;
import static edu.radyuk.foodblog.controller.command.SessionAttribute.USER;

/**
 * The type Logout command.
 */
public class LogoutCommandCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        request.getSession().removeAttribute(USER);
        return new CommandResponse(HOME_PAGE_REDIRECT, RoutingType.REDIRECT);
    }
}
