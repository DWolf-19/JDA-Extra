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
package com.dwolfnineteen.jdaextra.models.subcommands.groups;

import com.dwolfnineteen.jdaextra.commands.subcommandgroups.BaseSubcommandGroup;
import com.dwolfnineteen.jdaextra.models.subcommands.PrefixSubcommandProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class PrefixSubcommandGroupProperties extends SubcommandGroupProperties {
    /**
     *
     */
    protected String name;

    /**
     *
     */
    protected String description;

    /**
     *
     */
    protected List<PrefixSubcommandProperties> subcommands;

    {
        subcommands = new ArrayList<>();
    }

    /**
     * @param name
     * @param description
     */
    public PrefixSubcommandGroupProperties(@NotNull String name, @Nullable String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return
     */
    @Override
    public @NotNull String getName() {
        return name;
    }

    /**
     * @return 
     */
    @Override
    public @Nullable String getDescription() {
        return description;
    }

    /**
     * @return
     */
    public @NotNull List<PrefixSubcommandProperties> getSubcommands() {
        return subcommands;
    }

    /**
     * @param groupClass 
     * @return
     */
    @Override
    public @NotNull PrefixSubcommandGroupProperties setGroupClass(@NotNull BaseSubcommandGroup groupClass) {
        this.groupClass = groupClass;

        return this;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public @NotNull PrefixSubcommandGroupProperties setName(@NotNull String name) {
        this.name = name;
        
        return this;
    }

    /**
     * @param description
     * @return
     */
    @Override
    public @NotNull PrefixSubcommandGroupProperties setDescription(@Nullable String description) {
        this.description = description;
        
        return this;
    }

    /**
     * @param subcommands
     * @return
     */
    public @NotNull PrefixSubcommandGroupProperties addSubcommands(@NotNull Collection<? extends PrefixSubcommandProperties> subcommands) {
        this.subcommands.addAll(subcommands);

        return this;
    }

    /**
     * @param subcommands
     * @return
     */
    public @NotNull PrefixSubcommandGroupProperties addSubcommands(@NotNull PrefixSubcommandProperties... subcommands) {
        this.subcommands.addAll(Arrays.asList(subcommands));

        return this;
    }

    // TODO: Removing subcommands
}
