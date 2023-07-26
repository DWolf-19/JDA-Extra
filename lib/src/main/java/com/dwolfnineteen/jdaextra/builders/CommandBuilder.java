package com.dwolfnineteen.jdaextra.builders;

import com.dwolfnineteen.jdaextra.commands.Command;
import com.dwolfnineteen.jdaextra.models.CommandModel;

public abstract class CommandBuilder {
    protected Command command;

    // TODO: remove duplicate code
    public abstract CommandModel buildModel();
}
