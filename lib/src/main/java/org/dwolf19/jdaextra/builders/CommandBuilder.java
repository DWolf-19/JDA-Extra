package org.dwolf19.jdaextra.builders;

import org.dwolf19.jdaextra.commands.Command;
import org.dwolf19.jdaextra.models.CommandModel;

public abstract class CommandBuilder {
    protected Command command;

    // TODO: remove duplicate code
    public abstract CommandModel buildModel();
}
