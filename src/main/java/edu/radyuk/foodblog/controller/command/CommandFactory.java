package edu.radyuk.foodblog.controller.command;

import java.util.EnumMap;

public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private EnumMap<CommandType, ClientCommand> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        // commands.put(INVALID, new InvalidCommand());
    }

    public ClientCommand getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }

    public static CommandFactory getInstance() {
        return instance;
    }
}
