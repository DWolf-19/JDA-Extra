package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import org.dwolf19.jdaextra.CommandClient;

import org.jetbrains.annotations.NotNull;

public class PrefixCommandEvent extends MessageReceivedEvent {

    private final MessageReceivedEvent event;
    private final CommandClient client;

    public PrefixCommandEvent(@NotNull MessageReceivedEvent event, @NotNull CommandClient client) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());

        this.event = event;
        this.client = client;
    }

    @NotNull
    public CommandClient getClient() {
        return client;
    }

    @NotNull
    public MessageCreateAction reply(String content) {
        return event.getMessage().reply(content);
    }

    @NotNull
    public MessageCreateAction reply(MessageCreateData message) {
        return event.getMessage().reply(message);
    }

}
