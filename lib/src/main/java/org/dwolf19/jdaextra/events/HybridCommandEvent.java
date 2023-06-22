package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.GenericEvent;

import org.dwolf19.jdaextra.CommandClient;

import org.jetbrains.annotations.NotNull;

public class HybridCommandEvent implements CommandEvent {
    private final GenericEvent event;
    private final CommandClient client;

    public HybridCommandEvent(@NotNull GenericEvent event, @NotNull CommandClient client) {
        this.event = event;
        this.client = client;
    }

    @NotNull
    public GenericEvent getEvent() {
        return event;
    }

    @NotNull
    public CommandClient getClient() {
        return client;
    }

    @NotNull
    public String getName() {
        if (event instanceof SlashCommandEvent)
            return ((SlashCommandEvent) event).getName();

//        else if (event instanceof PrefixCommandEvent)
//            return ((PrefixCommandEvent) event).getName();

        throw new RuntimeException();
    }
}
