package org.dwolf19.jdaextra.prefix;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.dwolf19.jdaextra.JDAExtra;
import org.dwolf19.jdaextra.events.PrefixCommandEvent;
import org.dwolf19.jdaextra.models.PrefixCommandModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class PrefixCommandParser {
    private final JDAExtra jdaExtra;
    private final MessageReceivedEvent event;
    private final HashMap<String, PrefixCommandModel> commandsModels;

    private String commandName;
    private PrefixCommandEvent parsedEvent;

    public PrefixCommandParser(@NotNull MessageReceivedEvent event,
                               @NotNull JDAExtra jdaExtra,
                               @NotNull HashMap<String, PrefixCommandModel> commandsModels) {
        this.event = event;
        this.jdaExtra = jdaExtra;
        this.commandsModels = commandsModels;
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
    public String getCommandName() {
        return commandName;
    }

    @Nullable
    public PrefixCommandEvent getParsedEvent() {
        return parsedEvent;
    }

    public PrefixCommandModel getModel() {
        return commandsModels.get(commandName);
    }

    public void build() {
        final String content = event.getMessage().getContentRaw();

        final String prefix = jdaExtra.getPrefix();
        final String mention = event.getJDA().getSelfUser().getAsMention();

        final boolean whenMention = jdaExtra.isWhenMention();

        if (content.startsWith(prefix) || (whenMention && content.startsWith(mention))) {
            final String trigger = content.startsWith(prefix) ? prefix : mention + " ";

            String[] parts = content.substring(trigger.length()).split(" ");

            PrefixCommandModel model = commandsModels.get(parts[0]);

            if (model == null) {
                commandName = parts[0];

                return;
            }

//            final ArrayList<String> args = new ArrayList<>();
//            Collections.addAll(args, Arrays.copyOfRange(parts, 1, parts.length));
//
//            entity.setArgs(args);

            parsedEvent = new PrefixCommandEvent(event, jdaExtra, trigger, parts[0], model.getDescription());
        }
    }
}
