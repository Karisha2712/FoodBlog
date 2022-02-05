package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_404_PAGE;
import static edu.radyuk.foodblog.controller.command.RoutingType.REDIRECT;

/**
 * The type Invalid command.
 */
public class InvalidCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return new CommandResponse(ERROR_404_PAGE, REDIRECT);
    }
}
