package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.Event;
import org.dwolf19.jdaextra.CommandClient;

public abstract class HybridCommandEvent {

    private final Event event;
    private final CommandClient client;

    public HybridCommandEvent(Event event, CommandClient client) {
        this.event = event;
        this.client = client;
    }

    private Event getSourceEvent() {
        return event;
    }

    public CommandClient getClient() {
        return client;
    }

    public JDA getJDA() {
        return event.getJDA();
    }

}
