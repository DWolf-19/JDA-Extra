package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PrefixCommandEvent extends MessageReceivedEvent {

    public PrefixCommandEvent(MessageReceivedEvent event) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());
    }

}
