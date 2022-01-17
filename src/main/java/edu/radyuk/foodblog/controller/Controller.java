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
            default: {
                logger.log(Level.ERROR, "Invalid routing type");
                res.sendRedirect(ERROR_404_PAGE);
                //TODO change redirection to error 500 page
            }
        }
    }

}
