package org.dwolf19.jdaextra.parsers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.dwolf19.jdaextra.CommandClient;
import org.dwolf19.jdaextra.models.PrefixCommandModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrefixCommandParser {
    private final CommandClient client;
    private final MessageReceivedEvent event;

    public PrefixCommandParser(@NotNull MessageReceivedEvent event, @NotNull CommandClient client) {
        this.event = event;
        this.client = client;
    }

    @NotNull
    public CommandClient getClient() {
        return client;
    }

    @NotNull
    public MessageReceivedEvent getEvent() {
        return event;
    }

    @Nullable
    public PrefixCommandModel buildModel() {
        PrefixCommandModel model = new PrefixCommandModel();
        String content = event.getMessage().getContentRaw();

        final String prefix = client.getPrefix();

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
