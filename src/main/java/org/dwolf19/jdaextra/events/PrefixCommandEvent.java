package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;

import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.dwolf19.jdaextra.CommandClient;

public class PrefixCommandEvent extends MessageReceivedEvent {

    private final MessageReceivedEvent event;
    private final CommandClient client;

    public PrefixCommandEvent(MessageReceivedEvent event, CommandClient client) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());

        this.event = event;
        this.client = client;
    }

    public CommandClient getClient() {
        return client;
    }

    public MessageCreateAction reply(String content) {
        return event.getMessage().reply(content);
    }

    public MessageCreateAction reply(MessageCreateData message) {
        return event.getMessage().reply(message);
    }

}
