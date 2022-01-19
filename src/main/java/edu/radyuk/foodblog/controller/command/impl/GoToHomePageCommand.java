package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.HOME_PAGE;
import static edu.radyuk.foodblog.controller.command.RoutingType.FORWARD;

public class GoToHomePageCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return new CommandResponse(HOME_PAGE, FORWARD);
    }
}
