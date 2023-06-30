package org.dwolf19.jdaextra.parsers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.dwolf19.jdaextra.JDAExtra;
import org.dwolf19.jdaextra.models.PrefixCommandModel;

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
    public PrefixCommandModel buildModel() {
        PrefixCommandModel model = new PrefixCommandModel();
        String content = event.getMessage().getContentRaw();

        final String prefix = jdaExtra.getPrefix();

        if (content.startsWith(prefix)) {
            model.setPrefix(prefix);

            String[] parts = content.substring(prefix.length()).split(" ");

            model.setName(parts[0]);

            final ArrayList<String> args = new ArrayList<>();
            Collections.addAll(args, Arrays.copyOfRange(parts, 1, parts.length));

            model.setArgs(args);
        } else
            return null;

        return model;
    }
}
