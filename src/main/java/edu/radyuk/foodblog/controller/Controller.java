package edu.radyuk.foodblog.controller;

import edu.radyuk.foodblog.controller.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static edu.radyuk.foodblog.controller.command.PagePath.ERROR_404_PAGE;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * The type Controller.
 */
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processCommand(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processCommand(req, res);
    }

    private void processCommand(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        ClientCommand clientCommand = CommandFactory.getInstance().getCommand(commandName);
        CommandResponse commandResponse = clientCommand.execute(req);
        RoutingType routingType = commandResponse.getRoutingType();
        String resultPage = commandResponse.getResultPage();
        switch (routingType) {
            case FORWARD: {
                req.getRequestDispatcher(resultPage).forward(req, res);
                break;
            }
            case REDIRECT: {
                res.sendRedirect(req.getContextPath() + resultPage);
                break;
            }
            case ERROR: {
                res.sendError(resultPage.equals(ERROR_404_PAGE) ? SC_NOT_FOUND : SC_INTERNAL_SERVER_ERROR);
                break;
            }
            default: {
                logger.log(Level.ERROR, "Invalid routing type");
                res.sendError(SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

}
