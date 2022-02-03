package edu.radyuk.foodblog.controller.command;

public enum CommandType {
    INVALID,
    GO_TO_HOME_PAGE,
    GO_TO_SIGN_IN_PAGE,
    GO_TO_SIGN_UP_PAGE,
    GO_TO_ABOUT_PAGE,
    GO_TO_RECIPES_PAGE,
    VIEW_FULL_RECIPE,
    GO_TO_EDIT_BLOGGER_INFO,
    GO_TO_ADD_NEW_POST,
    GO_TO_PROFILE_PAGE,
    CHANGE_LANGUAGE,
    EDIT_BLOGGER_INFO,
    ADD_RECIPE_POST,
    DELETE_POST,
    COMMENT,
    LOGOUT,
    SIGN_IN,
    SIGN_UP;

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
