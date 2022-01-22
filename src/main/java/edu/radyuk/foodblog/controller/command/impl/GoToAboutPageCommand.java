package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.ABOUT_US_PAGE;
import static edu.radyuk.foodblog.controller.command.RoutingType.FORWARD;

public class GoToAboutPageCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return new CommandResponse(ABOUT_US_PAGE, FORWARD);
    }
}
