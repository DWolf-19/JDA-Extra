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
package com.dwolfnineteen.jdaextra.options.data;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Option data container for {@link com.dwolfnineteen.jdaextra.commands.SlashCommand SlashCommand}.
 * <br>
 * This class used by {@link com.dwolfnineteen.jdaextra.builders builders} for options building.
 */
public class SlashOptionData implements CommandOptionData, GeneralOptionData {
    private final OptionData data;

    /**
     * Construct new basic slash option data container without advanced settings.
     *
     * @param type The {@link OptionType} for this option.
     * @param name The name for this option.
     * @param description The description for this option.
     */
    public SlashOptionData(@NotNull OptionType type, @NotNull String name, @NotNull String description) {
        data = new OptionData(type, name, description);
    }

    /**
     * Construct new basic slash option data container.
     *
     * @param type The {@link OptionType} for this option.
     * @param name The name for this option.
     * @param description The description for this option.
     * @param isRequired Whether this option required.
     */
    public SlashOptionData(@NotNull OptionType type,
                           @NotNull String name,
                           @NotNull String description,
                           boolean isRequired) {
        data = new OptionData(type, name, description, isRequired);
    }

    /**
     * Construct new basic slash option data container.
     *
     * @param type The {@link OptionType} for this option.
     * @param name The name for this option.
     * @param description The description for this option.
     * @param isRequired Whether this option required.
     * @param isAutoComplete Whether this option supports autocomplete.
     */
    public SlashOptionData(@NotNull OptionType type,
                           @NotNull String name,
                           @NotNull String description,
                           boolean isRequired,
                           boolean isAutoComplete) {
        data = new OptionData(type, name, description, isRequired, isAutoComplete);
    }

    @Override
    @NotNull
    public OptionType getType() {
        return data.getType();
    }

    @Override
    @NotNull
    public String getName() {
        return data.getName();
    }

    @Override
    @NotNull
    public String getDescription() {
        return data.getDescription();
    }

    @Override
    public boolean isRequired() {
        return data.isRequired();
    }

    @Override
    public boolean isAutoComplete() {
        return data.isRequired();
    }

    @Override
    @Nullable
    public Number getMinValue() {
        return data.getMinValue();
    }

    @Override
    @Nullable
    public Number getMaxValue() {
        return data.getMaxValue();
    }

    @Override
    @Nullable
    public Integer getMinLength() {
        return data.getMinLength();
    }

    @Override
    @Nullable
    public Integer getMaxLength() {
        return data.getMaxLength();
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setName(@NotNull String name) {
        data.setName(name);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setDescription(@NotNull String description) {
        data.setDescription(description);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setRequired(boolean required) {
        data.setRequired(required);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setAutoComplete(boolean autoComplete) {
        data.setAutoComplete(autoComplete);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setMinValue(long minValue) {
        data.setMinValue(minValue);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setMinValue(double minValue) {
        data.setMinValue(minValue);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setMaxValue(long maxValue) {
        data.setMaxValue(maxValue);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setMaxValue(double maxValue) {
        data.setMaxValue(maxValue);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setRequiredRange(long minValue, long maxValue) {
        data.setRequiredRange(minValue, maxValue);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setRequiredRange(double minValue, double maxValue) {
        data.setRequiredRange(minValue, maxValue);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setMinLength(int minLength) {
        data.setMinLength(minLength);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setMaxLength(int maxLength) {
        data.setMaxLength(maxLength);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData setRequiredLength(int minLength, int maxLength) {
        data.setRequiredLength(minLength, maxLength);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData addChoice(@NotNull String name, double value) {
        data.addChoice(name, value);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData addChoice(@NotNull String name, long value) {
        data.addChoice(name, value);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData addChoice(@NotNull String name, @NotNull String value) {
        data.addChoice(name, value);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData addChoices(@NotNull Command.Choice... choices) {
        data.addChoices(choices);

        return this;
    }

    /**
     * @return Current {@link SlashOptionData} instance, for chaining.
     */
    @Override
    @NotNull
    public SlashOptionData addChoices(@NotNull Collection<? extends Command.Choice> choices) {
        data.addChoices(choices);

        return this;
    }

    @Override
    @NotNull
    public OptionData toGeneralOptionData() {
        return data;
    }
}
