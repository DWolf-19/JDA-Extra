package org.dwolf19.jdaextra.examples;

import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.events.PrefixCommandEvent;

public class PrefixCommandExample extends PrefixCommand {

    PrefixCommandExample() {
        this.name = "example";
        this.description = "My beautiful command!";
    }

    @Override
    public void logic(PrefixCommandEvent event) {
        event.reply("Hello, Discord!").queue();
    }

}
