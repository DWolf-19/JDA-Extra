package com.dwolfnineteen.jdaextra.events;

import com.dwolfnineteen.jdaextra.JDAExtra;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

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
