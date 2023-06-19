package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import org.dwolf19.jdaextra.CommandClient;

import org.jetbrains.annotations.NotNull;

public class SlashCommandEvent extends SlashCommandInteractionEvent implements CommandEvent {
    private final CommandClient client;

    public SlashCommandEvent(@NotNull SlashCommandInteractionEvent event, @NotNull CommandClient client) {
        super(event.getJDA(), event.getResponseNumber(), event.getInteraction());

        this.client = client;
    }

    @NotNull
    public CommandClient getClient() {
        return client;
    }
}
