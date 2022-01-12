package edu.radyuk.foodblog.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface ClientCommand {
    CommandResponse execute(HttpServletRequest request);
}
