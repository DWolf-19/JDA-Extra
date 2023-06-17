package org.dwolf19.jdaextra;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import org.jetbrains.annotations.NotNull;

public class CommandClient implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent)
            onReadyEvent((ReadyEvent)event);

        else if (event instanceof MessageReceivedEvent)
            onMessageReceivedEvent((MessageReceivedEvent)event);

        else if (event instanceof SlashCommandInteractionEvent)
            onSlashCommandInteractionEvent((SlashCommandInteractionEvent)event);
    }

    private void onReadyEvent(@NotNull ReadyEvent event) {
        // TODO: add logic
    }

    private void onMessageReceivedEvent(@NotNull MessageReceivedEvent event) {
        // TODO: add logic
    }

    private void onSlashCommandInteractionEvent(@NotNull SlashCommandInteractionEvent event) {
        // TODO: add logic
    }
}
