package org.dwolf19.jdaextra.commands;

import org.dwolf19.jdaextra.events.SlashCommandEvent;

public abstract class SlashCommand extends Command {
    public abstract void executeSlashLogic(SlashCommandEvent event);

    // TODO: add logic
}
