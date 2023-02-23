package org.dwolf19.jdaextra.commands;

import org.dwolf19.jdaextra.Interaction;

public abstract class Command extends Interaction {

    protected String name;
    protected String description;
    protected String[] aliases;
    protected boolean nsfwOnly = false;

    protected String getName() {
        return name;
    }

    protected String getDescription() {
        return description;
    }

    protected String[] getAliases() {
        return aliases;
    }

    protected boolean isNsfwOnly() {
        return nsfwOnly;
    }

    protected abstract void logic();

}
