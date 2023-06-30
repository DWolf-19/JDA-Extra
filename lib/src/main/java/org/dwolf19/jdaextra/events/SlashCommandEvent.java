package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import org.dwolf19.jdaextra.JDAExtra;

import org.jetbrains.annotations.NotNull;

public class SlashCommandEvent extends SlashCommandInteractionEvent implements CommandEvent {
    private final JDAExtra jdaExtra;

    public SlashCommandEvent(@NotNull SlashCommandInteractionEvent event, @NotNull JDAExtra jdaExtra) {
        super(event.getJDA(), event.getResponseNumber(), event.getInteraction());

        this.jdaExtra = jdaExtra;
    }

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }
}
