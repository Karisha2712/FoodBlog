package edu.radyuk.foodblog.controller.command.impl.blogger;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

public class CommentCommand implements ClientCommand {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        return null;
        //TODO
    }
}
