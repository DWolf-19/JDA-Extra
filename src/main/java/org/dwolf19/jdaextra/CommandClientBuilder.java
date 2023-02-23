package org.dwolf19.jdaextra;

import org.dwolf19.jdaextra.commands.MessageCommand;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.commands.SlashCommand;
import org.dwolf19.jdaextra.commands.UserCommand;

public class CommandClientBuilder {

    protected String prefix;

    public CommandClient build() {
        return new CommandClient(prefix);
    }

    public CommandClientBuilder setPrefix(String prefix) {
        this.prefix = prefix;

        return this;
    }

    public CommandClientBuilder addPrefixCommands(PrefixCommand... command) {
        // TODO: add logic
        return this;
    }

    public CommandClientBuilder addSlashCommands(SlashCommand... command) {
        // TODO: add logic
        return this;
    }

    public CommandClientBuilder addUserCommands(UserCommand... command) {
        // TODO: add logic
        return this;
    }

    public CommandClientBuilder addMessageCommands(MessageCommand... command) {
        // TODO: add logic
        return this;
    }

}
