package edu.radyuk.foodblog.controller.command;

import edu.radyuk.foodblog.controller.command.impl.*;

import java.util.EnumMap;

import static edu.radyuk.foodblog.controller.command.CommandType.*;

public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private EnumMap<CommandType, ClientCommand> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(INVALID, new InvalidCommand());
        commands.put(GO_TO_HOME_PAGE, new GoToHomePageCommand());
        commands.put(GO_TO_SIGN_IN_PAGE, new GoToSignInPageCommand());
        commands.put(GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(SIGN_IN, new SignInCommand());
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public ClientCommand getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }
}
