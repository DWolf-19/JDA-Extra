/*
 * Copyright (c) 2023 DWolf Nineteen & The JDA-Extra contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.dwolfnineteen.jdaextra;

import com.dwolfnineteen.jdaextra.commands.HybridCommand;
import com.dwolfnineteen.jdaextra.commands.PrefixCommand;
import com.dwolfnineteen.jdaextra.commands.SlashCommand;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for comfortable configuration {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra} instance.
 */
public class JDAExtraBuilder {
    private String prefix;
    private boolean whenMention;

    private final List<HybridCommand> hybridCommands;
    private final List<PrefixCommand> prefixCommands;
    private final List<SlashCommand> slashCommands;

    {
        whenMention = false;

        hybridCommands = new ArrayList<>();
        prefixCommands = new ArrayList<>();
        slashCommands = new ArrayList<>();
    }

    /**
     * Sets the prefix for prefix/hybrid commands.
     *
     * @param prefix The prefix.
     * @return Current {@link com.dwolfnineteen.jdaextra.JDAExtraBuilder JDAExtraBuilder} instance, for chaining.
     */
    @NotNull
    public JDAExtraBuilder setPrefix(@NotNull String prefix) {
        this.prefix = prefix;

        return this;
    }

    /**
     * Sets the prefix and whether bot react to its mention as a prefix to {@code True}.
     *
     * @param prefix The prefix for prefix/hybrid commands.
     * @return Current {@link com.dwolfnineteen.jdaextra.JDAExtraBuilder JDAExtraBuilder} instance, for chaining.
     */
    @NotNull
    public JDAExtraBuilder setWhenMentionOr(@NotNull String prefix) {
        this.prefix = prefix;
        whenMention = true;

        return this;
    }

    /**
     * Add varargs of {@link com.dwolfnineteen.jdaextra.commands.HybridCommand HybridCommand} to
     * {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra}.
     *
     * @param commands {@link com.dwolfnineteen.jdaextra.commands.HybridCommand HybridCommand} varargs.
     * @return Current {@link com.dwolfnineteen.jdaextra.JDAExtraBuilder JDAExtraBuilder} instance, for chaining.
     */
    @NotNull
    public JDAExtraBuilder addHybridCommands(@NotNull HybridCommand... commands) {
        hybridCommands.addAll(Arrays.asList(commands));

        return this;
    }

    /**
     * Add varargs of {@link com.dwolfnineteen.jdaextra.commands.PrefixCommand PrefixCommand} to
     * {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra}.
     *
     * @param commands {@link com.dwolfnineteen.jdaextra.commands.PrefixCommand PrefixCommand} varargs.
     * @return Current {@link com.dwolfnineteen.jdaextra.JDAExtraBuilder JDAExtraBuilder} instance, for chaining.
     */
    @NotNull
    public JDAExtraBuilder addPrefixCommands(@NotNull PrefixCommand... commands) {
        prefixCommands.addAll(Arrays.asList(commands));

        return this;
    }

    /**
     * Add varargs of {@link com.dwolfnineteen.jdaextra.commands.SlashCommand SlashCommand} to
     * {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra}.
     *
     * @param commands {@link com.dwolfnineteen.jdaextra.commands.SlashCommand SlashCommand} varargs.
     * @return Current {@link com.dwolfnineteen.jdaextra.JDAExtraBuilder JDAExtraBuilder} instance, for chaining.
     */
    @NotNull
    public JDAExtraBuilder addSlashCommands(@NotNull SlashCommand... commands) {
        slashCommands.addAll(Arrays.asList(commands));

        return this;
    }

    /**
     * Build new {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra} instance.
     *
     * @return New {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra} instance.
     */
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
