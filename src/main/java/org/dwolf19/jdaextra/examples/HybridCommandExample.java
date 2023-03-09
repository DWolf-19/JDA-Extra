package org.dwolf19.jdaextra.examples;

import org.dwolf19.jdaextra.commands.HybridCommand;
import org.dwolf19.jdaextra.events.HybridCommandEvent;

public class HybridCommandExample extends HybridCommand {

    HybridCommandExample() {
        this.name = "example";
        this.description = "My beautiful command!";
    }

    @Override
    public void logic(HybridCommandEvent<?> event) {
        event.reply("Hello, Discord!").queue();
    }

}
