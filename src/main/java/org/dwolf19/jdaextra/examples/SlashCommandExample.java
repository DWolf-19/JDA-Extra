package org.dwolf19.jdaextra.examples;

import org.dwolf19.jdaextra.commands.SlashCommand;
import org.dwolf19.jdaextra.events.SlashCommandEvent;

public class SlashCommandExample extends SlashCommand {

    SlashCommandExample() {
        this.name = "example";
        this.description = "My beautiful command!";
    }

    @Override
    public void logic(SlashCommandEvent event) {
        event.reply("Hello, Discord!").queue();
    }

}
