package edu.radyuk.foodblog.controller.command;

import edu.radyuk.foodblog.controller.command.impl.*;
import edu.radyuk.foodblog.controller.command.impl.admin.ChangeUserStatusCommand;
import edu.radyuk.foodblog.controller.command.impl.admin.DeleteCommentCommand;
import edu.radyuk.foodblog.controller.command.impl.admin.GoToAdminPageCommand;
import edu.radyuk.foodblog.controller.command.impl.blogger.*;

import java.util.EnumMap;

import static edu.radyuk.foodblog.controller.command.CommandType.*;

/**
 * The type Command factory.
 */
public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private EnumMap<CommandType, ClientCommand> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(INVALID, new InvalidCommand());
        commands.put(GO_TO_HOME_PAGE, new GoToHomePageCommand());
        commands.put(GO_TO_SIGN_IN_PAGE, new GoToSignInPageCommand());
        commands.put(GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(GO_TO_ABOUT_PAGE, new GoToAboutPageCommand());
        commands.put(GO_TO_RECIPES_PAGE, new GoToRecipesPageCommand());
        commands.put(VIEW_FULL_RECIPE, new ViewFullRecipeCommand());
        commands.put(GO_TO_EDIT_BLOGGER_INFO, new GoToEditBloggerInfoCommand());
        commands.put(GO_TO_ADD_NEW_POST, new GoToAddNewPostCommand());
        commands.put(GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
        commands.put(EDIT_BLOGGER_INFO, new EditBloggerInfoCommand());
        commands.put(ADD_RECIPE_POST, new AddRecipePostCommand());
        commands.put(GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
        commands.put(CHANGE_USER_STATUS, new ChangeUserStatusCommand());
        commands.put(DELETE_COMMENT, new DeleteCommentCommand());
        commands.put(COMMENT, new CommentCommand());
        commands.put(DELETE_POST, new DeleteRecipePostCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(SIGN_IN, new SignInCommand());
        commands.put(SIGN_UP, new SignUpCommand());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Gets command.
     *
     * @param command the command
     * @return the command
     */
    public ClientCommand getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }
}
