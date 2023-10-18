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

import java.util.Collection;

public class SlashOptionData extends OptionData implements CommandOptionData {
    public SlashOptionData(@NotNull OptionType type, @NotNull String name, @NotNull String description) {
        super(type, name, description);
    }

    public SlashOptionData(@NotNull OptionType type,
                           @NotNull String name,
                           @NotNull String description,
                           boolean isRequired) {
        super(type, name, description, isRequired);
    }

    public SlashOptionData(@NotNull OptionType type,
                           @NotNull String name,
                           @NotNull String description,
                           boolean isRequired,
                           boolean isAutoComplete) {
        super(type, name, description, isRequired, isAutoComplete);
    }

    @Override
    @NotNull
    public SlashOptionData setName(@NotNull String name) {
        super.setName(name);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setDescription(@NotNull String description) {
        super.setDescription(description);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setRequired(boolean required) {
        super.setRequired(required);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setAutoComplete(boolean autoComplete) {
        super.setAutoComplete(autoComplete);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setMinValue(long minValue) {
        super.setMinValue(minValue);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setMinValue(double minValue) {
        super.setMinValue(minValue);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setMaxValue(long maxValue) {
        super.setMaxValue(maxValue);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setMaxValue(double maxValue) {
        super.setMaxValue(maxValue);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setRequiredRange(long minValue, long maxValue) {
        super.setRequiredRange(minValue, maxValue);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setRequiredRange(double minValue, double maxValue) {
        super.setRequiredRange(minValue, maxValue);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setMinLength(int minLength) {
        super.setMinLength(minLength);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setMaxLength(int maxLength) {
        super.setMaxLength(maxLength);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData setRequiredLength(int minLength, int maxLength) {
        super.setRequiredLength(minLength, maxLength);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData addChoice(@NotNull String name, double value) {
        super.addChoice(name, value);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData addChoice(@NotNull String name, long value) {
        super.addChoice(name, value);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData addChoice(@NotNull String name, @NotNull String value) {
        super.addChoice(name, value);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData addChoices(Command.@NotNull Choice... choices) {
        super.addChoices(choices);

        return this;
    }

    @Override
    @NotNull
    public SlashOptionData addChoices(@NotNull Collection<? extends Command.Choice> choices) {
        super.addChoices(choices);

        return this;
    }
}
