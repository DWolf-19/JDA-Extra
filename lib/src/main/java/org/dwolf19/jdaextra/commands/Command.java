package org.dwolf19.jdaextra.commands;

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;

public abstract class Command {
    protected String name;
    protected String description;
    protected Options options;
    protected DefaultMemberPermissions defaultMemberPermissions;
    protected boolean guildOnly;
    protected boolean nsfw;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Options getOptions() {
        return options;
    }

    public DefaultMemberPermissions getDefaultMemberPermissions() {
        return defaultMemberPermissions;
    }

    public boolean isGuildOnly() {
        return guildOnly;
    }

    public boolean isNSFW() {
        return nsfw;
    }
}
