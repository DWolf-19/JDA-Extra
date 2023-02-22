package org.dwolf19.jdaextra;

import org.dwolf19.jdaextra.commands.MessageCommand;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.commands.SlashCommand;
import org.dwolf19.jdaextra.commands.UserCommand;

public class CommandClientBuilder {

    protected String prefix;

    CommandClientBuilder build() {
        CommandClient client = new CommandClient(prefix);

        return this;
    }

    CommandClientBuilder setPrefix(String prefix) {
        this.prefix = prefix;

        return this;
    }

    CommandClientBuilder addPrefixCommands(PrefixCommand... command) {
        // TODO: add logic
        return this;
    }

    CommandClientBuilder addSlashCommands(SlashCommand... command) {
        // TODO: add logic
        return this;
    }

    CommandClientBuilder addUserCommands(UserCommand... command) {
        // TODO: add logic
        return this;
    }

    CommandClientBuilder addMessageCommands(MessageCommand... command) {
        // TODO: add logic
        return this;
    }

}
