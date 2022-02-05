package edu.radyuk.foodblog.controller.listener;


import edu.radyuk.foodblog.controller.command.SessionAttribute;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * The type Http session listener.
 */
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";
    private static final String DEFAULT_PREVIOUS_QUERY = "/controller?command=home_page";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(SessionAttribute.LOCALE, DEFAULT_LOCALE);
        session.setAttribute(SessionAttribute.PREVIOUS_QUERY, DEFAULT_PREVIOUS_QUERY);
    }

}
