package edu.radyuk.foodblog.controller.command;

import edu.radyuk.foodblog.controller.command.impl.HomePageCommand;

import java.util.EnumMap;

import static edu.radyuk.foodblog.controller.command.CommandType.HOME_PAGE;

public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private EnumMap<CommandType, ClientCommand> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        // commands.put(INVALID, new InvalidCommand());
        commands.put(HOME_PAGE, new HomePageCommand());
    }

    public ClientCommand getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }

    public static CommandFactory getInstance() {
        return instance;
    }
}
