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

import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import com.dwolfnineteen.jdaextra.options.data.SlashOptionData;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.interactions.commands.localization.LocalizationFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
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
    public @NotNull String getDescription() {
        return description;
    }

    /**
     * All command options as a {@link List}.
     *
     * @return The command options.
     */
    public @NotNull List<SlashOptionData> getOptions() {
        return options;
    }

    /**
     * {@inheritDoc}
     *
     * @param nameLocalizations {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    public @NotNull SlashCommandModel setNameLocalizations(@NotNull Map<DiscordLocale, String> nameLocalizations) {
        this.nameLocalizations = nameLocalizations;

        return this;
    }

    /**
     * Sets the command description.
     *
     * @param description The command description.
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    public @NotNull SlashCommandModel setDescription(@NotNull String description) {
        this.description = description;

        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param descriptionLocalizations {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    public @NotNull SlashCommandModel setDescriptionLocalizations(@NotNull Map<DiscordLocale, String> descriptionLocalizations) {
        this.descriptionLocalizations = descriptionLocalizations;

        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param localizationFunction {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    public @NotNull SlashCommandModel setLocalizationFunction(@NotNull LocalizationFunction localizationFunction) {
        this.localizationFunction = localizationFunction;

        return this;
    }

    /**
     * Sets the command options as a {@link List}.
     *
     * @param options The command options.
     * @return Current {@link SlashCommandModel} instance, for chaining.
     */
    public @NotNull SlashCommandModel setOptions(@NotNull List<SlashOptionData> options) {
        this.options = options;

        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param command {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    public @NotNull SlashCommandModel setCommand(@NotNull BaseCommand command) {
        this.command = command;

        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param main {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     * @see #getMain() getMain()
     */
    @Override
    public @NotNull SlashCommandModel setMain(@Nullable Method main) {
        this.main = main;

        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param name {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    public @NotNull SlashCommandModel setName(@NotNull String name) {
        this.name = name;

        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param guildOnly {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    public @NotNull SlashCommandModel setGuildOnly(boolean guildOnly) {
        this.guildOnly = guildOnly;

        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param nsfw {@inheritDoc}
     * @return The {@link SlashCommandModel} instance, for chaining.
     */
    @Override
    public @NotNull SlashCommandModel setNSFW(boolean nsfw) {
        this.nsfw = nsfw;

        return this;
    }
}
