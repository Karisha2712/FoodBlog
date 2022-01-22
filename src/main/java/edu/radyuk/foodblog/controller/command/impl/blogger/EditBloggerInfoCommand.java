package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.PagePath;
import edu.radyuk.foodblog.controller.command.RoutingType;

import javax.servlet.http.HttpServletRequest;

public class EditBloggerInfoCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return new CommandResponse(PagePath.EDIT_INFO_PAGE, RoutingType.FORWARD);
        //TODO
    }
}
