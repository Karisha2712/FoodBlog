package edu.radyuk.foodblog.controller.command;

public class MessageKey {
    public static final String INVALID_SIGN_UP_FORM_INPUT = "sign_up.error.invalid_input";
    public static final String UNAVAILABLE_ACCESS="sign_up.error.unavailable_access";
    public static final String UNAVAILABLE_EMAIL = "sign_up.error.unavailable_email";
    public static final String UNAVAILABLE_LOGIN = "sign_up.error.unavailable_login";
    public static final String INVALID_SIGN_IN_FORM_INPUT = "sign_in_error.incorrect_data";
    public static final String INVALID_COMMENT = "comment_error.invalid_input";
    public static final String EMPTY_POSTS_TABLE = "recipes.no_any_recipes";
    public static final String NO_COMMENTS_FOR_POST = "comments.no_any_comments";
    public static final String INVALID_EDIT_INFO_FORM_INPUT = "edit_info_error.incorrect_data";
    public static final String INVALID_ADD_POST_FORM_INPUT = "add_post.incorrect_data";

    private MessageKey() {
    }
}
