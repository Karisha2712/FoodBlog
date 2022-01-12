package edu.radyuk.foodblog.controller.command;

import java.util.EnumMap;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private EnumMap<CommandType, AbstractCommand> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        // commands.put(INVALID, new InvalidCommand());
    }

    public AbstractCommand getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }


    public CommandFactory getInstance() {
        return instance;
    }
}
