package org.dwolf19.jdaextra.commands;

import org.dwolf19.jdaextra.events.HybridCommandEvent;

public abstract class HybridCommand extends Command {
    public abstract void executeHybridLogic(HybridCommandEvent event);

    // TODO: add logic
}
