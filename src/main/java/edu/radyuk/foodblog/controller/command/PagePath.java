package edu.radyuk.foodblog.controller.command;

public final class PagePath {
    public static final String HOME_PAGE = "WEB-INF/pages/home_page.jsp";
    public static final String SIGN_IN_PAGE = "WEB-INF/pages/sign_in_page.jsp";
    public static final String SIGN_UP_PAGE = "WEB-INF/pages/sign_up_page.jsp";
    public static final String ERROR_404_PAGE = "/static_pages/error_404_page.jsp";
    public static final String ERROR_500_PAGE = "/static_pages/error_500_page.jsp";

    public static final String SIGN_UP_PAGE_REDIRECT = "/controller?command=go_to_sign_up_page";


    private PagePath() {
    }
}
