package edu.radyuk.foodblog.controller.command;

import edu.radyuk.foodblog.controller.command.impl.HomePageCommand;
import edu.radyuk.foodblog.controller.command.impl.InvalidCommand;
import edu.radyuk.foodblog.controller.command.impl.SignInPageCommand;

import java.util.EnumMap;

import static edu.radyuk.foodblog.controller.command.CommandType.*;

public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private EnumMap<CommandType, ClientCommand> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(INVALID, new InvalidCommand());
        commands.put(HOME_PAGE, new HomePageCommand());
        commands.put(SIGN_IN_PAGE, new SignInPageCommand());
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public ClientCommand getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }
}
