package org.dwolf19.jdaextra;

import org.dwolf19.jdaextra.commands.*;

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

    public CommandClientBuilder addHybridCommands(HybridCommand... commands) {
        for (HybridCommand command : commands)
            hybridCommands.put(command.getName(), command);

        return this;
    }

    public CommandClientBuilder addPrefixCommands(PrefixCommand... commands) {
        for (PrefixCommand command : commands)
            prefixCommands.put(command.getName(), command);

        return this;
    }

    public CommandClientBuilder addSlashCommands(SlashCommand... commands) {
        for (SlashCommand command : commands)
            slashCommands.put(command.getName(), command);

        return this;
    }

}
