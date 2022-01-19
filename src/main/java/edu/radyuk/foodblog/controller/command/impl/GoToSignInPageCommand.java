package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.SIGN_IN_PAGE;
import static edu.radyuk.foodblog.controller.command.RoutingType.FORWARD;

public class GoToSignInPageCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return new CommandResponse(SIGN_IN_PAGE, FORWARD);
    }
}
