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
import com.dwolfnineteen.jdaextra.models.subcommands.HybridSubcommandProperties;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class HybridSubcommandGroupProperties extends SlashLikeSubcommandGroupProperties {
    /**
     *
     */
    protected List<HybridSubcommandProperties> subcommands;

    {
        subcommands = new ArrayList<>();
    }

    /**
     * @param name
     * @param description
     */
    public HybridSubcommandGroupProperties(@NotNull String name, @NotNull String description) {
        generalData = new SubcommandGroupData(name, description);
    }

    /**
     * @return
     */
    public @NotNull List<HybridSubcommandProperties> getSubcommands() {
        return subcommands;
    }

    /**
     * @param groupClass
     * @return
     */
    @Override
    public @NotNull HybridSubcommandGroupProperties setGroupClass(@NotNull BaseSubcommandGroup groupClass) {
        this.groupClass = groupClass;

        return this;
    }

    /**
     * @param name 
     * @return
     */
    @Override
    public @NotNull HybridSubcommandGroupProperties setName(@NotNull String name) {
        generalData.setName(name);

        return this;
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    @Override
    public @NotNull HybridSubcommandGroupProperties setNameLocalization(@NotNull DiscordLocale locale,
                                                                        @NotNull String name) {
        generalData.setNameLocalization(locale, name);
        
        return this;
    }

    /**
     * @param localizations
     * @return
     */
    @Override
    public @NotNull HybridSubcommandGroupProperties setNameLocalizations(@NotNull Map<DiscordLocale, String> localizations) {
        generalData.setNameLocalizations(localizations);

        return this;
    }

    /**
     * @param description 
     * @return
     */
    @Override
    public @NotNull HybridSubcommandGroupProperties setDescription(@NotNull String description) {
        generalData.setDescription(description);

        return this;
    }

    /**
     * @param locale 
     * @param name
     * @return
     */
    @Override
    public @NotNull HybridSubcommandGroupProperties setDescriptionLocalization(@NotNull DiscordLocale locale,
                                                                               @NotNull String name) {
        generalData.setDescriptionLocalization(locale, name);
        
        return this;
    }

    /**
     * @param localizations
     * @return
     */
    @Override
    public @NotNull HybridSubcommandGroupProperties setDescriptionLocalizations(@NotNull Map<DiscordLocale, String> localizations) {
        generalData.setDescriptionLocalizations(localizations);

        return this;
    }

    /**
     * @param subcommands
     * @return
     */
    public @NotNull HybridSubcommandGroupProperties addSubcommands(@NotNull Collection<? extends HybridSubcommandProperties> subcommands) {
        this.subcommands.addAll(subcommands);

        return this;
    }

    /**
     * @param subcommands
     * @return
     */
    public @NotNull HybridSubcommandGroupProperties addSubcommands(@NotNull HybridSubcommandProperties... subcommands) {
        this.subcommands.addAll(Arrays.asList(subcommands));

        return this;
    }

    // TODO: Removing subcommands

    /**
     * @return
     */
    @Override
    public @NotNull SubcommandGroupData toGeneralSubcommandGroupData() {
        return generalData.addSubcommands(subcommands.stream()
                .map(HybridSubcommandProperties::toGeneralSubcommandData)
                .collect(Collectors.toList()));
    }
}
