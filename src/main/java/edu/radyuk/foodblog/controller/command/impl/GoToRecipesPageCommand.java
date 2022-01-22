package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.RECIPES_PAGE;
import static edu.radyuk.foodblog.controller.command.RoutingType.FORWARD;

public class GoToRecipesPageCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return new CommandResponse(RECIPES_PAGE, FORWARD);
    }
}
