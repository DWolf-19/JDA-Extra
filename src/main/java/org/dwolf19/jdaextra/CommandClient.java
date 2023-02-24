package org.dwolf19.jdaextra;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import org.dwolf19.jdaextra.commands.Command;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.commands.SlashCommand;
import org.dwolf19.jdaextra.events.SlashCommandEvent;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Optional;

public class CommandClient implements EventListener {

    private final String prefix;
    private final boolean useMention;

    private final LinkedList<Command> hybridCommands;
    private final LinkedList<PrefixCommand> prefixCommands;
    private final LinkedList<SlashCommand> slashCommands;

    CommandClient(String prefix,
                  boolean useMention,
                  LinkedList<Command> hybridCommands,
                  LinkedList<PrefixCommand> prefixCommands,
                  LinkedList<SlashCommand> slashCommands) {
        this.prefix = prefix;
        this.useMention = useMention;

        // Bind commands
        this.hybridCommands = hybridCommands;
        this.prefixCommands = prefixCommands;
        this.slashCommands = slashCommands;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent)
            onReadyEvent((ReadyEvent)event);

        else if (event instanceof MessageReceivedEvent)
            onMessageReceivedEvent((MessageReceivedEvent)event);

        else if (event instanceof SlashCommandInteractionEvent)
            onSlashCommandInteractionEvent((SlashCommandInteractionEvent)event);

        else if (event instanceof UserContextInteractionEvent)
            onUserContextInteractionEvent((UserContextInteractionEvent)event);

        else if (event instanceof MessageContextInteractionEvent)
            onMessageContextInteractionEvent((MessageContextInteractionEvent)event);
    }

    private void onReadyEvent(ReadyEvent event) {
        // TODO: add logging of the start of the wrapper
        bindInteractionsToJDA(event.getJDA());
    }

    private void bindInteractionsToJDA(JDA jda) {
        // TODO: write logic
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
        final SlashCommandEvent commandEvent = new SlashCommandEvent(event);
        // TODO: add logic
    }

    private void onUserContextInteractionEvent(UserContextInteractionEvent event) {
        // TODO: add logic
    }

    private void onMessageContextInteractionEvent(MessageContextInteractionEvent event) {
        // TODO: add logic
    }

}
