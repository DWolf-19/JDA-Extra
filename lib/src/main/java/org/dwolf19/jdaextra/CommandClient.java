package org.dwolf19.jdaextra;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

public class CommandClient extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // TODO: add logic
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // TODO: add logic
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // TODO: add logic
    }
}
