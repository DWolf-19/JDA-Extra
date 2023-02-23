package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SlashCommandEvent extends SlashCommandInteractionEvent {

    public SlashCommandEvent(SlashCommandInteractionEvent event) {
        super(event.getJDA(), event.getResponseNumber(), event);
    }

}
