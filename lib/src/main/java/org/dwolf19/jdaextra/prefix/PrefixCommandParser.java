package org.dwolf19.jdaextra.prefix;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.dwolf19.jdaextra.JDAExtra;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrefixCommandParser {
    private final JDAExtra jdaExtra;
    private final MessageReceivedEvent event;

    public PrefixCommandParser(@NotNull MessageReceivedEvent event, @NotNull JDAExtra jdaExtra) {
        this.event = event;
        this.jdaExtra = jdaExtra;
    }

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }

    @NotNull
    public MessageReceivedEvent getEvent() {
        return event;
    }

    @Nullable
    public PrefixCommandEntity buildEntity() {
        PrefixCommandEntity entity = new PrefixCommandEntity();
        String content = event.getMessage().getContentRaw();

        final String prefix = jdaExtra.getPrefix();
        final String mention = event.getJDA().getSelfUser().getAsMention();

        final boolean whenMention = jdaExtra.isWhenMention();

        if (content.startsWith(prefix) || (whenMention && content.startsWith(mention))) {
            final String trigger = content.startsWith(prefix) ? prefix : mention + " ";

            entity.setPrefix(trigger);

            String[] parts = content.substring(trigger.length()).split(" ");

            entity.setName(parts[0]);

            final ArrayList<String> args = new ArrayList<>();
            Collections.addAll(args, Arrays.copyOfRange(parts, 1, parts.length));

            entity.setArgs(args);
        } else
            return null;

        return entity;
    }
}
