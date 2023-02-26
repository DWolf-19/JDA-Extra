package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import org.dwolf19.jdaextra.CommandClient;

public class SlashCommandEvent extends SlashCommandInteractionEvent {

    private final CommandClient client;

    public SlashCommandEvent(SlashCommandInteractionEvent event, CommandClient client) {
        super(event.getJDA(), event.getResponseNumber(), event);

        this.client = client;
    }

    public CommandClient getClient() {
        return client;
    }

}
