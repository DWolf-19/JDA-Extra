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
import com.dwolfnineteen.jdaextra.options.data.SlashOptionData;
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
public class SlashSubcommandProperties extends SlashLikeSubcommandProperties {
    /**
     *
     */
    protected List<SlashOptionData> options;

    {
        options = new ArrayList<>();
    }

    public SlashSubcommandProperties(@NotNull String name, @NotNull String description) {
        super(name, description);
    }

    /**
     * @return
     */
    @Override
    public @NotNull Method getEntryPoint() {
        return entryPoint;
    }

    /**
     * @return
     */
    @Override
    public @NotNull List<SlashOptionData> getOptions() {
        return options;
    }

    /**
     * @param entryPoint
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties setEntryPoint(@NotNull Method entryPoint) {
        this.entryPoint = entryPoint;

        return this;
    }

    /**
     * @param name 
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties setName(@NotNull String name) {
        generalData.setName(name);

        return this;
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties setNameLocalization(DiscordLocale locale, String name) {
        return null;
    }

    /**
     * @param localizations
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties setNameLocalizations(@NotNull Map<DiscordLocale, String> localizations) {
        generalData.setNameLocalizations(localizations);

        return this;
    }

    /**
     * @param description 
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties setDescription(@NotNull String description) {
        generalData.setDescription(description);

        return this;
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties setDescriptionLocalization(DiscordLocale locale, String name) {
        return null;
    }

    /**
     * @param localizations
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties setDescriptionLocalizations(@NotNull Map<DiscordLocale, String> localizations) {
        generalData.setDescriptionLocalizations(localizations);

        return this;
    }

    /**
     * @param type 
     * @param name
     * @param description
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties addOption(OptionType type, String name, String description) {
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
    public @NotNull SlashSubcommandProperties addOption(OptionType type,
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
    public @NotNull SlashSubcommandProperties addOption(OptionType type,
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
    public @NotNull SlashSubcommandProperties addOptions(@NotNull Collection<CommandOptionData> options) {
        this.options.addAll(options.stream()
                .map(o -> (SlashOptionData) o)
                .collect(Collectors.toList()));

        return this;
    }

    // TODO: Change logic project-wide in this way (interface in parameter and then cast)
    //  This will make sure that all necessary methods are implemented by adding signature to abstract class
    /**
     * @param options
     * @return
     */
    @Override
    public @NotNull SlashSubcommandProperties addOptions(CommandOptionData... options) {
        this.options.addAll(Arrays.stream(options)
                .map(o -> (SlashOptionData) o)
                .collect(Collectors.toList()));

        return this;
    }

    /**
     * @return
     */
    @Override
    public @NotNull SubcommandData toGeneralSubcommandData() {
        return generalData.addOptions(options.stream()
                .map(SlashOptionData::toGeneralOptionData)
                .collect(Collectors.toList()));
    }
}
