package org.dwolf19.jdaextra;

import org.dwolf19.jdaextra.commands.*;

import java.util.Arrays;
import java.util.HashMap;

public class CommandClientBuilder {

    private String prefix;
    private boolean useMention = false;

    private final HashMap<String, HybridCommand> hybridCommands = new HashMap<>();
    private final HashMap<String, PrefixCommand> prefixCommands = new HashMap<>();
    private final HashMap<String, SlashCommand> slashCommands = new HashMap<>();

    public CommandClient build() {
        return new CommandClient(
                prefix,
                useMention,
                hybridCommands,
                prefixCommands,
                slashCommands);
    }

    public CommandClientBuilder setPrefix(String prefix) {
        this.prefix = prefix;

        return this;
    }

    public CommandClientBuilder setWhenMentionedOr(String prefix) {
        this.prefix = prefix;
        this.useMention = true;

        return this;
    }

    public CommandClientBuilder addHybridCommands(Command... commands) {
        hybridCommands.addAll(Arrays.asList(commands));

        return this;
    }

    public CommandClientBuilder addPrefixCommands(PrefixCommand... commands) {
        prefixCommands.addAll(Arrays.asList(commands));

        return this;
    }

    public CommandClientBuilder addSlashCommands(SlashCommand... commands) {
        slashCommands.addAll(Arrays.asList(commands));

        return this;
    }

    public CommandClientBuilder addUserCommands(UserCommand... commands) {
        // TODO: add logic
        return this;
    }

    public CommandClientBuilder addMessageCommands(MessageCommand... commands) {
        // TODO: add logic
        return this;
    }

}
