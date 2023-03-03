package org.dwolf19.jdaextra.commands;

import org.dwolf19.jdaextra.Interaction;

public abstract class Command extends Interaction {

    protected String name;
    protected String description;
    protected String[] aliases;
    protected boolean nsfwOnly = false;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public boolean isNsfwOnly() {
        return nsfwOnly;
    }

}
