package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.GenericEvent;

import org.dwolf19.jdaextra.JDAExtra;
import org.dwolf19.jdaextra.exceptions.InvalidHybridEventException;

import org.jetbrains.annotations.NotNull;

public class HybridCommandEvent implements CommandEvent {
    private final GenericEvent event;
    private final JDAExtra jdaExtra;

    public HybridCommandEvent(@NotNull GenericEvent event, @NotNull JDAExtra jdaExtra) {
        this.event = event;
        this.jdaExtra = jdaExtra;
    }

    @NotNull
    public GenericEvent getEvent() {
        return event;
    }

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }

    @NotNull
    public String getName() {
        if (event instanceof SlashCommandEvent)
            return ((SlashCommandEvent) event).getName();

//        else if (event instanceof PrefixCommandEvent)
//            return ((PrefixCommandEvent) event).getName();

        throw new InvalidHybridEventException();
    }
}
