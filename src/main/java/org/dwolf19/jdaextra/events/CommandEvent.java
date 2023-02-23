package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.GenericEvent;

public class CommandEvent {

    GenericEvent event;
    String prefix;

    CommandEvent(GenericEvent event, String prefix) {
        this.event = event;
        this.prefix = prefix;
    }

    public GenericEvent getEvent() {
        return event;
    }

    public String getPrefix() {
        return prefix;
    }

}
