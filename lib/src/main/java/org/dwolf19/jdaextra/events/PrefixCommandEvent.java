package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.dwolf19.jdaextra.JDAExtra;

import org.jetbrains.annotations.NotNull;

public class PrefixCommandEvent extends MessageReceivedEvent implements CommandEvent {
    private final JDAExtra jdaExtra;

    public PrefixCommandEvent(@NotNull MessageReceivedEvent event, @NotNull JDAExtra jdaExtra) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());

        this.jdaExtra = jdaExtra;
    }

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }
}
