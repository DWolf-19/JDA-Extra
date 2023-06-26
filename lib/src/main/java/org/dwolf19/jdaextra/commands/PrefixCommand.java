package org.dwolf19.jdaextra.commands;

import org.dwolf19.jdaextra.events.PrefixCommandEvent;

public abstract class PrefixCommand extends Command {
    public abstract void executePrefixLogic(PrefixCommandEvent event);

    // TODO: add logic
}
