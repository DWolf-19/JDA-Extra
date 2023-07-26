package com.dwolfnineteen.jdaextra.events;

import com.dwolfnineteen.jdaextra.JDAExtra;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.jetbrains.annotations.NotNull;

public class PrefixCommandEvent extends MessageReceivedEvent implements CommandEvent {
    private final JDAExtra jdaExtra;
    private final String prefix;
    private final String name;
    private final String description;

    public PrefixCommandEvent(@NotNull MessageReceivedEvent event,
                              @NotNull JDAExtra jdaExtra,
                              @NotNull String prefix,
                              @NotNull String name,
                              @NotNull String description) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());

        this.jdaExtra = jdaExtra;
        this.prefix = prefix;
        this.name = name;
        this.description = description;
    }

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }
}
