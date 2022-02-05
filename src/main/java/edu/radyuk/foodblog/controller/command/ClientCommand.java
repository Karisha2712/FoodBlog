package edu.radyuk.foodblog.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Client command.
 */
public interface ClientCommand {
    /**
     * Execute command response.
     *
     * @param request the request
     * @return the command response
     */
    CommandResponse execute(HttpServletRequest request);
}
