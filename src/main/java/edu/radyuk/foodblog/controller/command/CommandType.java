package edu.radyuk.foodblog.controller.command;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * Invalid command type.
     */
    INVALID,
    /**
     * Go to sign in page command type.
     */
    GO_TO_SIGN_IN_PAGE,
    /**
     * Go to sign up page command type.
     */
    GO_TO_SIGN_UP_PAGE,
    /**
     * Go to home page command type.
     */
    GO_TO_HOME_PAGE,
    /**
     * Go to about page command type.
     */
    GO_TO_ABOUT_PAGE,
    /**
     * Go to recipes page command type.
     */
    GO_TO_RECIPES_PAGE,
    /**
     * View full recipe command type.
     */
    VIEW_FULL_RECIPE,
    /**
     * Change language command type.
     */
    CHANGE_LANGUAGE,
    /**
     * Go to profile page command type.
     */
    GO_TO_PROFILE_PAGE,
    /**
     * Sign in command type.
     */
    SIGN_IN,
    /**
     * Sign up command type.
     */
    SIGN_UP,

    /**
     * Go to edit blogger info command type.
     */
    GO_TO_EDIT_BLOGGER_INFO,
    /**
     * Go to add new post command type.
     */
    GO_TO_ADD_NEW_POST,
    /**
     * Edit blogger info command type.
     */
    EDIT_BLOGGER_INFO,
    /**
     * Add recipe post command type.
     */
    ADD_RECIPE_POST,
    /**
     * Delete comment command type.
     */
    DELETE_COMMENT,
    /**
     * Delete post command type.
     */
    DELETE_POST,
    /**
     * Comment command type.
     */
    COMMENT,

    /**
     * Go to admin page command type.
     */
    GO_TO_ADMIN_PAGE,
    /**
     * Change user status command type.
     */
    CHANGE_USER_STATUS,

    /**
     * Logout command type.
     */
    LOGOUT;

    /**
     * Gets command type.
     *
     * @param command the command
     * @return the command type
     */
    public static CommandType getCommandType(String command) {
        if (command == null) {
            return INVALID;
        }
        CommandType type;
        try {
            type = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            type = INVALID;
        }
        return type;
    }
}
