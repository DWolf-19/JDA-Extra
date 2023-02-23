package org.dwolf19.jdaextra;

import org.dwolf19.jdaextra.events.CommandEvent;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

public class CommandClient implements EventListener {

    protected String prefix;
    protected boolean useMention;

    CommandClient(String prefix, boolean useMention) {
        this.prefix = prefix;
        this.useMention = useMention;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent)
            onMessageReceivedEvent((MessageReceivedEvent)event);

        else if (event instanceof SlashCommandInteractionEvent)
            onSlashCommandInteractionEvent((SlashCommandInteractionEvent)event);

        else if (event instanceof UserContextInteractionEvent)
            onUserContextInteractionEvent((UserContextInteractionEvent)event);

        else if (event instanceof MessageContextInteractionEvent)
            onMessageContextInteractionEvent((MessageContextInteractionEvent)event);
    }

    private void onMessageReceivedEvent(MessageReceivedEvent event) {
        Optional<PrefixCommandStructure> command = Optional.ofNullable(parseMessage(event));

        // If the message is not a command
        if (command.isEmpty())
            return;
    }

    private PrefixCommandStructure parseMessage(MessageReceivedEvent event) {
        String rawContent = event.getMessage().getContentRaw();
        PrefixCommandStructure command = new PrefixCommandStructure();

        command.setPrefix(prefix);

        if (rawContent.startsWith(prefix)) {
            for (int symbol = prefix.length(); symbol < rawContent.length(); symbol++)
                if (Character.isWhitespace(rawContent.charAt(symbol))) {
                    command.setName(rawContent.substring(prefix.length(), symbol));
                    break;
                }
        } else
            return null;

        return command;
    }

    private static class PrefixCommandStructure {
        String prefix;
        String name;

        PrefixCommandStructure setPrefix(String prefix) {
            this.prefix = prefix;

            return this;
        }

        String getPrefix() {
            return this.prefix;
        }

        PrefixCommandStructure setName(String name) {
            this.name = name;

            return this;
        }

        String getName() {
            return this.name;
        }

    }

    private void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {

    }

    private void onUserContextInteractionEvent(UserContextInteractionEvent event) {
    }

    private void onMessageContextInteractionEvent(MessageContextInteractionEvent event) {
    }

}
