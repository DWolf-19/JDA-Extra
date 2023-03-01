package org.dwolf19.jdaextra;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import org.dwolf19.jdaextra.commands.HybridCommand;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.commands.SlashCommand;
import org.dwolf19.jdaextra.events.SlashCommandEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CommandClient implements EventListener {

    private final String prefix;
    private final boolean useMention;

    private final List<HybridCommand> hybridCommands;
    private final List<PrefixCommand> prefixCommands;
    private final List<SlashCommand> slashCommands;

    CommandClient(@NotNull String prefix,
                  boolean useMention,
                  @NotNull List<HybridCommand> hybridCommands,
                  @NotNull List<PrefixCommand> prefixCommands,
                  @NotNull List<SlashCommand> slashCommands) {
        this.prefix = prefix;
        this.useMention = useMention;

        this.hybridCommands = hybridCommands;
        this.prefixCommands = prefixCommands;
        this.slashCommands = slashCommands;
    }

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    public boolean isUseMention() {
        return useMention;
    }

    @NotNull
    public List<HybridCommand> getHybridCommands() {
        return hybridCommands;
    }

    @NotNull
    public List<SlashCommand> getSlashCommands() {
        return slashCommands;
    }

    @NotNull
    public List<PrefixCommand> getPrefixCommands() {
        return prefixCommands;
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

    private void onReadyEvent(@NotNull ReadyEvent event) {
        // TODO: add logging of the start of the wrapper
        bindInteractionsToJDA(event.getJDA());
    }

    private void bindInteractionsToJDA(@NotNull JDA jda) {
        // TODO: write logic
    }

    private void onMessageReceivedEvent(@NotNull MessageReceivedEvent event) {
        Optional<PrefixCommandStructure> command = Optional.ofNullable(parseMessage(event));

        // If the message is not a command
        if (command.isEmpty())
            return;
    }

    @Nullable
    private PrefixCommandStructure parseMessage(@NotNull MessageReceivedEvent event) {
        String rawContent = event.getMessage().getContentRaw();
        PrefixCommandStructure command = new PrefixCommandStructure();

        if (rawContent.startsWith(prefix)) {
            command.setPrefix(prefix);

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

        private String prefix;
        private String name;

        @NotNull
        public PrefixCommandStructure setPrefix(@NotNull String prefix) {
            this.prefix = prefix;

            return this;
        }

        @NotNull
        public String getPrefix() {
            return prefix;
        }

        @NotNull
        public PrefixCommandStructure setName(@NotNull String name) {
            this.name = name;

            return this;
        }

        @NotNull
        public String getName() {
            return name;
        }

    }

    private void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {
        final SlashCommandEvent commandEvent = new SlashCommandEvent(event, this);

        // TODO: add logic
    }

    private void onUserContextInteractionEvent(UserContextInteractionEvent event) {
        // TODO: add logic
    }

    private void onMessageContextInteractionEvent(MessageContextInteractionEvent event) {
        // TODO: add logic
    }

}
