package edu.radyuk.foodblog.controller.command.impl;

import edu.radyuk.foodblog.controller.command.ClientCommand;
import edu.radyuk.foodblog.controller.command.CommandResponse;
import edu.radyuk.foodblog.controller.command.RoutingType;
import edu.radyuk.foodblog.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ClientCommand {
    private static final String RUSSIAN_LOCALE = "ru";
    private static final String ENGLISH_LOCALE = "en";

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String previousQuery = session.getAttribute(SessionAttribute.PREVIOUS_QUERY).toString();
        String currentLocale = session.getAttribute(SessionAttribute.LOCALE).toString();
        if (currentLocale != null) {
            String locale = currentLocale.equals(ENGLISH_LOCALE) ? RUSSIAN_LOCALE : ENGLISH_LOCALE;
            session.setAttribute(SessionAttribute.LOCALE, locale);
        }
        return new CommandResponse(previousQuery, RoutingType.REDIRECT);
    }

}
