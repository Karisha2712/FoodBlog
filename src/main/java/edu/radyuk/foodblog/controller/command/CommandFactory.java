package edu.radyuk.foodblog.controller.command;

import edu.radyuk.foodblog.controller.command.impl.HomePageCommand;
import edu.radyuk.foodblog.controller.command.impl.InvalidCommand;

import java.util.EnumMap;

import static edu.radyuk.foodblog.controller.command.CommandType.HOME_PAGE;
import static edu.radyuk.foodblog.controller.command.CommandType.INVALID;

public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private EnumMap<CommandType, ClientCommand> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(INVALID, new InvalidCommand());
        commands.put(HOME_PAGE, new HomePageCommand());
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public ClientCommand getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }
}
