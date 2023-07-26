package com.dwolfnineteen.jdaextra;

import com.dwolfnineteen.jdaextra.commands.HybridCommand;
import com.dwolfnineteen.jdaextra.commands.PrefixCommand;
import com.dwolfnineteen.jdaextra.commands.SlashCommand;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class JDAExtraBuilder {
    private String prefix;
    private boolean whenMention = false;

    private final ArrayList<HybridCommand> hybridCommands = new ArrayList<>();
    private final ArrayList<PrefixCommand> prefixCommands = new ArrayList<>();
    private final ArrayList<SlashCommand> slashCommands = new ArrayList<>();

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
        hybridCommands.addAll(Arrays.asList(commands));

        return this;
    }

    @NotNull
    public JDAExtraBuilder addPrefixCommands(@NotNull PrefixCommand... commands) {
        prefixCommands.addAll(Arrays.asList(commands));

        return this;
    }

    @NotNull
    public JDAExtraBuilder addSlashCommands(@NotNull SlashCommand... commands) {
        slashCommands.addAll(Arrays.asList(commands));

        return this;
    }

    @NotNull
    public JDAExtra build() {
        return new JDAExtra(
                prefix,
                whenMention,
                hybridCommands,
                prefixCommands,
                slashCommands);
    }
}
