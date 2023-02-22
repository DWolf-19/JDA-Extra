package org.dwolf19.jdaextra;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import org.jetbrains.annotations.NotNull;

public class CommandClient implements EventListener {

    protected String prefix;

    CommandClient(String prefix) {
        this.prefix = prefix;
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
    }

    private void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {
    }

    private void onUserContextInteractionEvent(UserContextInteractionEvent event) {
    }

    private void onMessageContextInteractionEvent(MessageContextInteractionEvent event) {
    }

}
