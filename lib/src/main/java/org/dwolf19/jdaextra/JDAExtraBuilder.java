package org.dwolf19.jdaextra;

import org.dwolf19.jdaextra.commands.HybridCommand;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.commands.SlashCommand;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class JDAExtraBuilder {
    private String prefix;
    private boolean whenMention = false;

    private final HashMap<String, HybridCommand> hybridCommands = new HashMap<>();
    private final HashMap<String, PrefixCommand> prefixCommands = new HashMap<>();
    private final HashMap<String, SlashCommand> slashCommands = new HashMap<>();

    @NotNull
    public JDAExtraBuilder setPrefix(@NotNull String prefix) {
        this.prefix = prefix;

        return this;
    }

    @NotNull
    public JDAExtraBuilder setWhenMentionOr(@NotNull String prefix) {
        this.prefix = prefix;
        whenMention = true;

        return this;
    }

    @NotNull
    public JDAExtraBuilder addHybridCommands(@NotNull HybridCommand... commands) {
        for (HybridCommand command : commands)
            hybridCommands.put(command.getName(), command);

        return this;
    }

    @NotNull
    public JDAExtraBuilder addPrefixCommands(@NotNull PrefixCommand... commands) {
        for (PrefixCommand command : commands)
            prefixCommands.put(command.getName(), command);

        return this;
    }

    @NotNull
    public JDAExtraBuilder addSlashCommands(@NotNull SlashCommand... commands) {
        for (SlashCommand command : commands)
            slashCommands.put(command.getName(), command);

        return this;
    }

    @NotNull
    public JDAExtra build() {
        return new JDAExtra(
                prefix,
                whenMention,
                hybridCommands,
                prefixCommands,
                slashCommands
        );
    }
}
