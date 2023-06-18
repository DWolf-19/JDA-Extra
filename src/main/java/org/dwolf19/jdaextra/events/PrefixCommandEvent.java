package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.dwolf19.jdaextra.CommandClient;

import org.jetbrains.annotations.NotNull;

public class PrefixCommandEvent extends MessageReceivedEvent {
    private final CommandClient client;

    public PrefixCommandEvent(@NotNull MessageReceivedEvent event, @NotNull CommandClient client) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());

        this.client = client;
    }

    @NotNull
    public CommandClient getClient() {
        return client;
    }
}
