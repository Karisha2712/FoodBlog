package edu.radyuk.foodblog.controller.command;

public enum CommandType {
    INVALID,
    HOME_PAGE,
    LOGIN,
    LOGOUT;

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
