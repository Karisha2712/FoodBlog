package edu.radyuk.foodblog.controller.command;

/**
 * The type Message key.
 */
public class MessageKey {
    /**
     * The constant INVALID_SIGN_UP_FORM_INPUT.
     */
    public static final String INVALID_SIGN_UP_FORM_INPUT = "sign_up.error.invalid_input";
    /**
     * The constant UNAVAILABLE_ACCESS.
     */
    public static final String UNAVAILABLE_ACCESS = "sign_up.error.unavailable_access";
    /**
     * The constant UNAVAILABLE_EMAIL.
     */
    public static final String UNAVAILABLE_EMAIL = "sign_up.error.unavailable_email";
    /**
     * The constant UNAVAILABLE_LOGIN.
     */
    public static final String UNAVAILABLE_LOGIN = "sign_up.error.unavailable_login";
    /**
     * The constant INVALID_SIGN_IN_FORM_INPUT.
     */
    public static final String INVALID_SIGN_IN_FORM_INPUT = "sign_in_error.incorrect_data";
    /**
     * The constant INVALID_COMMENT.
     */
    public static final String INVALID_COMMENT = "comment_error.invalid_input";
    /**
     * The constant EMPTY_POSTS_TABLE.
     */
    public static final String EMPTY_POSTS_TABLE = "recipes.no_any_recipes";
    /**
     * The constant INVALID_EDIT_INFO_FORM_INPUT.
     */
    public static final String INVALID_EDIT_INFO_FORM_INPUT = "edit_info_error.incorrect_data";
    /**
     * The constant INVALID_ADD_POST_FORM_INPUT.
     */
    public static final String INVALID_ADD_POST_FORM_INPUT = "add_post.incorrect_data";

    private MessageKey() {
    }
}
