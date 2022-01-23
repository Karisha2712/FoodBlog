package edu.radyuk.foodblog.controller.command;

public final class PagePath {
    public static final String HOME_PAGE = "WEB-INF/pages/home_page.jsp";
    public static final String SIGN_IN_PAGE = "WEB-INF/pages/sign_in_page.jsp";
    public static final String SIGN_UP_PAGE = "WEB-INF/pages/sign_up_page.jsp";
    public static final String ERROR_404_PAGE = "/error_pages/error_404_page.jsp";
    public static final String ERROR_500_PAGE = "/error_pages/error_500_page.jsp";
    public static final String ABOUT_US_PAGE = "WEB-INF/pages/about_us_page.jsp";
    public static final String RECIPES_PAGE = "WEB-INF/pages/recipes_page.jsp";
    public static final String PROFILE_PAGE = "WEB-INF/pages/blogger/profile_page.jsp";
    public static final String SINGLE_RECIPE_PAGE = "WEB-INF/pages/single_recipe_page.jsp";
    public static final String EDIT_INFO_PAGE = "WEB-INF/pages/blogger/edit_info_page.jsp";
    public static final String ADD_RECIPE_POST_PAGE = "WEB-INF/pages/blogger/add_recipe_post_page.jsp";

    public static final String SIGN_UP_PAGE_REDIRECT = "/controller?command=go_to_sign_up_page";
    public static final String SIGN_IN_PAGE_REDIRECT = "/controller?command=go_to_sign_in_page";


    private PagePath() {
    }
}
