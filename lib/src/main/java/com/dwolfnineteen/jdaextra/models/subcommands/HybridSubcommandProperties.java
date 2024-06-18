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
package com.dwolfnineteen.jdaextra.models.subcommands;

import com.dwolfnineteen.jdaextra.options.data.CommandOptionData;
import com.dwolfnineteen.jdaextra.options.data.HybridOptionData;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: Add missing annotations
/**
 *
 */
public class HybridSubcommandProperties extends SlashLikeSubcommandProperties {
    /**
     *
     */
    protected List<HybridOptionData> options;

    {
        options = new ArrayList<>();
    }

    /**
     * @return
     */
    @Override
    public @NotNull Method getEntryPoint() {
        return entryPoint;
    }

    /**
     * @param name
     * @param description
     */
    public HybridSubcommandProperties(@NotNull String name, @NotNull String description) {
        super(name, description);
    }

    /**
     * @return 
     */
    @Override
    public List<HybridOptionData> getOptions() {
        return options;
    }

    /**
     * @param entryPoint
     * @return
     */
    @Override
    public @NotNull HybridSubcommandProperties setEntryPoint(@NotNull Method entryPoint) {
        this.entryPoint = entryPoint;

        return this;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public @NotNull HybridSubcommandProperties setName(@NotNull String name) {
        generalData.setName(name);

        return this;
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    @Override
    public HybridSubcommandProperties setNameLocalization(DiscordLocale locale, String name) {
        return null;
    }

    /**
     * @param localizations
     * @return
     */
    @Override
    public HybridSubcommandProperties setNameLocalizations(Map<DiscordLocale, String> localizations) {
        return null;
    }

    /**
     * @param description
     * @return
     */
    @Override
    public @NotNull HybridSubcommandProperties setDescription(@NotNull String description) {
        generalData.setDescription(description);

        return this;
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    @Override
    public HybridSubcommandProperties setDescriptionLocalization(DiscordLocale locale, String name) {
        return null;
    }

    /**
     * @param localizations
     * @return
     */
    @Override
    public HybridSubcommandProperties setDescriptionLocalizations(Map<DiscordLocale, String> localizations) {
        return null;
    }

    /**
     * @param type
     * @param name
     * @param description
     * @return
     */
    @Override
    public @NotNull HybridSubcommandProperties addOption(OptionType type, String name, String description) {
        generalData.addOption(type, name, description);

        return this;
    }

    /**
     * @param type
     * @param name
     * @param description
     * @param required
     * @return
     */
    @Override
    public @NotNull HybridSubcommandProperties addOption(OptionType type,
                                                         String name,
                                                         String description,
                                                         boolean required) {
        generalData.addOption(type, name, description, required);

        return this;
    }

    /**
     * @param type
     * @param name
     * @param description
     * @param required
     * @param autocomplete
     * @return
     */
    @Override
    public @NotNull HybridSubcommandProperties addOption(OptionType type,
                                                         String name,
                                                         String description,
                                                         boolean required,
                                                         boolean autocomplete) {
        generalData.addOption(type, name, description, required, autocomplete);

        return this;
    }

    /**
     * @param options 
     * @return
     */
    @Override
    public HybridSubcommandProperties addOptions(Collection<CommandOptionData> options) {
        this.options.addAll(options.stream()
                .map(o -> (HybridOptionData) o)
                .collect(Collectors.toList()));

        return this;
    }

    /**
     * @param options 
     * @return
     */
    @Override
    public HybridSubcommandProperties addOptions(CommandOptionData... options) {
        this.options.addAll(Arrays.stream(options)
                .map(o -> (HybridOptionData) o)
                .collect(Collectors.toList()));

        return this;
    }

    /**
     * @return
     */
    @Override
    public @NotNull SubcommandData toGeneralSubcommandData() {
        return generalData.addOptions(options.stream()
                .map(HybridOptionData::toGeneralOptionData)
                .collect(Collectors.toList()));
    }
}
