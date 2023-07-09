package org.dwolf19.jdaextra.exceptions;

public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String commandName) {
        super(String.format("Command '%s' not found", commandName));
    }
}
