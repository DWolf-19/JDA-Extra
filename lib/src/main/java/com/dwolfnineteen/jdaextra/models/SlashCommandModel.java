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
package com.dwolfnineteen.jdaextra.models;

import com.dwolfnineteen.jdaextra.options.data.SlashOptionData;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.localization.LocalizationFunction;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Slash command model.
 *
 * @see CommandModel
 */
public class SlashCommandModel extends SlashLikeCommandModel {
    private String description;
    private List<SlashOptionData> options;

    /**
     * The command description.
     *
     * @return The description.
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * All command options as a {@link List}.
     *
     * @return The command options.
     */
    @NotNull
    public List<SlashOptionData> getOptions() {
        return options;
    }

    /**
     * Sets multiple localizations of the command name.
     *
     * @param nameLocalizations {@link Map} of {@link DiscordLocale} and name on different languages.
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashCommandModel setNameLocalizations(@NotNull Map<DiscordLocale, String> nameLocalizations) {
        this.nameLocalizations = nameLocalizations;

        return this;
    }

    /**
     * Sets the command description.
     *
     * @param description The command description.
     * @return Current {@link SlashCommandModel} instance, for chaining.
     */
    @NotNull
    public SlashCommandModel setDescription(@NotNull String description) {
        this.description = description;

        return this;
    }

    /**
     * Sets multiple localizations of the command description.
     *
     * @param descriptionLocalizations {@link Map} of {@link DiscordLocale} and description on different languages.
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashCommandModel setDescriptionLocalizations(@NotNull Map<DiscordLocale, String> descriptionLocalizations) {
        this.descriptionLocalizations = descriptionLocalizations;

        return this;
    }

    /**
     * Sets the {@link LocalizationFunction} for this command.
     * This allows to localize the entire command.
     * <br>
     * Only accepts
     * {@link net.dv8tion.jda.api.interactions.commands.localization.ResourceBundleLocalizationFunction ResourceBundleLocalizationFunction}.
     *
     * @param localizationFunction The {@link LocalizationFunction}.
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashCommandModel setLocalizationFunction(@NotNull LocalizationFunction localizationFunction) {
        this.localizationFunction = localizationFunction;

        return this;
    }

    /**
     * Sets the command options as a {@link List}.
     *
     * @param options The command options.
     * @return Current {@link SlashCommandModel} instance, for chaining.
     */
    @NotNull
    public SlashCommandModel setOptions(@NotNull List<SlashOptionData> options) {
        this.options = options;

        return this;
    }
}
