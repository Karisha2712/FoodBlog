package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.PagePath;
import edu.radyuk.foodblog.controller.command.RoutingType;

import javax.servlet.http.HttpServletRequest;

public class ViewFullRecipeCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return new CommandResponse(PagePath.SINGLE_RECIPE_PAGE, RoutingType.FORWARD);
        //TODO
    }
}
