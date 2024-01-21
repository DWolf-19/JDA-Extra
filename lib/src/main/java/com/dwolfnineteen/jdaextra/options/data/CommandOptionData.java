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

import java.util.Collection;

/**
 * Basic interface for options data containers.
 */
public interface CommandOptionData {
    /**
     * The {@link net.dv8tion.jda.api.interactions.commands.OptionType OptionType} for this option.
     *
     * @return The {@link net.dv8tion.jda.api.interactions.commands.OptionType OptionType}.
     */
    OptionType getType();

    /**
     * The name for this option.
     *
     * @return The name.
     */
    String getName();

    /**
     * Description for this option.
     *
     * @return The description.
     */
    String getDescription();

    /**
     * Whether this option must be specified at command calling.
     *
     * @return {@code True}, if this option must be specified at command calling.
     */
    boolean isRequired();

    /**
     * Whether this option supports autocomplete.
     *
     * @return {@code True}, if this option supports autocomplete.
     */
    boolean isAutoComplete();

    /**
     * Min value for this option.
     *
     * @return Min value for this option.
     */
    Number getMinValue();

    /**
     * Max value for this option.
     *
     * @return value for this option.
     */
    Number getMaxValue();

    /**
     * Min length (in symbols) for this option.
     *
     * @return Min length for this option.
     */
    Integer getMinLength();

    /**
     * Max length (in symbols) for this option.
     *
     * @return Max length (in symbols) for this option.
     */
    Integer getMaxLength();

    /**
     * Sets name for this option.
     *
     * @param name The name for this option.
     * @return {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setName(String name);

    /**
     * Sets description for this option.
     *
     * @param description The description for this option.
     * @return {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setDescription(String description);

    /**
     * Sets whether this option required.
     *
     * @param required Whether this option required.
     * @return {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setRequired(boolean required);

    /**
     * Sets whether this option supports autocomplete.
     *
     * @param autoComplete Whether this option supports autocomplete.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setAutoComplete(boolean autoComplete);

    /**
     * Sets min value for this option as a {@code long}.
     *
     * @param minValue Min value for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setMinValue(long minValue);

    /**
     * Sets min value for this option as a {@code double}.
     *
     * @param minValue Min value for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setMinValue(double minValue);

    /**
     * Sets max value for this option as a {@code long}.
     *
     * @param maxValue Max value for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setMaxValue(long maxValue);

    /**
     * Sets max value for this option as a {@code double}.
     *
     * @param maxValue Max value for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setMaxValue(double maxValue);

    /**
     * Sets required range for this option as a {@code long} values.
     *
     * @param minValue Min value for this option.
     * @param maxValue Max value for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setRequiredRange(long minValue, long maxValue);

    /**
     * Sets required range for this option as a {@code double} values.
     *
     * @param minValue Min value for this option.
     * @param maxValue Max value for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setRequiredRange(double minValue, double maxValue);

    /**
     * Sets min length (in symbols) for this option.
     *
     * @param minLength Min length for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setMinLength(int minLength);

    /**
     * Sets max length (in symbols) for this option.
     *
     * @param maxLength Max length for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setMaxLength(int maxLength);

    /**
     * Sets required length for this option.
     *
     * @param minLength Min length for this option.
     * @param maxLength Max length for this option.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData setRequiredLength(int minLength, int maxLength);

    /**
     * Add predefined choice for this option.
     *
     * @param name The option name.
     * @param value The option value (as a {@code double}).
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData addChoice(String name, double value);

    /**
     * Add predefined choice for this option.
     *
     * @param name The option name.
     * @param value The option value (as a {@code long}).
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData addChoice(String name, long value);

    /**
     * Add predefined choice for this option.
     *
     * @param name The option name.
     * @param value The option value (as a {@link String String}).
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData addChoice(String name, String value);

    /**
     * Add predefined choice/choices for this option.
     *
     * @param choices {@link net.dv8tion.jda.api.interactions.commands.Command.Choice Command.Choice} varargs.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData addChoices(Command.Choice... choices);

    /**
     * Add predefined choices for this option.
     *
     * @param choices {@link java.util.Collection Collection} with
     * {@link net.dv8tion.jda.api.interactions.commands.Command.Choice Command.Choice}.
     * @return Current {@link com.dwolfnineteen.jdaextra.options.data.CommandOptionData CommandOptionData} instance,
     * for chaining.
     */
    CommandOptionData addChoices(Collection<? extends Command.Choice> choices);
}
