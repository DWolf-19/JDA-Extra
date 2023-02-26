package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.dwolf19.jdaextra.CommandClient;

public class PrefixCommandEvent extends MessageReceivedEvent {

    private final CommandClient client;

    public PrefixCommandEvent(MessageReceivedEvent event, CommandClient client) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());

        this.client = client;
    }

    public CommandClient getClient() {
        return client;
    }

}
