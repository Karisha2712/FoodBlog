package edu.radyuk.foodblog.controller.command;

/**
 * The type Page path.
 */
public final class PagePath {
    /**
     * The constant HOME_PAGE.
     */
    public static final String HOME_PAGE = "WEB-INF/pages/home_page.jsp";
    /**
     * The constant SIGN_IN_PAGE.
     */
    public static final String SIGN_IN_PAGE = "WEB-INF/pages/sign_in_page.jsp";
    /**
     * The constant SIGN_UP_PAGE.
     */
    public static final String SIGN_UP_PAGE = "WEB-INF/pages/sign_up_page.jsp";
    /**
     * The constant ERROR_404_PAGE.
     */
    public static final String ERROR_404_PAGE = "/error_pages/error_404_page.jsp";
    /**
     * The constant ERROR_500_PAGE.
     */
    public static final String ERROR_500_PAGE = "/error_pages/error_500_page.jsp";
    /**
     * The constant ABOUT_US_PAGE.
     */
    public static final String ABOUT_US_PAGE = "WEB-INF/pages/about_us_page.jsp";
    /**
     * The constant RECIPES_PAGE.
     */
    public static final String RECIPES_PAGE = "WEB-INF/pages/recipes_page.jsp";
    /**
     * The constant PROFILE_PAGE.
     */
    public static final String PROFILE_PAGE = "WEB-INF/pages/blogger/profile_page.jsp";
    /**
     * The constant SINGLE_RECIPE_PAGE.
     */
    public static final String SINGLE_RECIPE_PAGE = "WEB-INF/pages/single_recipe_page.jsp";
    /**
     * The constant EDIT_INFO_PAGE.
     */
    public static final String EDIT_INFO_PAGE = "WEB-INF/pages/blogger/edit_info_page.jsp";
    /**
     * The constant ADD_RECIPE_POST_PAGE.
     */
    public static final String ADD_RECIPE_POST_PAGE = "WEB-INF/pages/blogger/add_recipe_post_page.jsp";
    /**
     * The constant ADMIN_PAGE.
     */
    public static final String ADMIN_PAGE = "WEB-INF/pages/admin/admin_page.jsp";

    /**
     * The constant PROFILE_PAGE_REDIRECT.
     */
    public static final String PROFILE_PAGE_REDIRECT = "/controller?command=go_to_profile_page&user_id=";
    /**
     * The constant SIGN_UP_PAGE_REDIRECT.
     */
    public static final String SIGN_UP_PAGE_REDIRECT = "/controller?command=go_to_sign_up_page";
    /**
     * The constant HOME_PAGE_REDIRECT.
     */
    public static final String HOME_PAGE_REDIRECT = "/controller?command=go_to_home_page";
    /**
     * The constant SIGN_IN_PAGE_REDIRECT.
     */
    public static final String SIGN_IN_PAGE_REDIRECT = "/controller?command=go_to_sign_in_page";
    /**
     * The constant ADMIN_PAGE_REDIRECT.
     */
    public static final String ADMIN_PAGE_REDIRECT = "/controller?command=go_to_admin_page";
    /**
     * The constant VIEW_FULL_RECIPE_REDIRECT.
     */
    public static final String VIEW_FULL_RECIPE_REDIRECT = "/controller?command=view_full_recipe&post_id=";


    private PagePath() {
    }
}
