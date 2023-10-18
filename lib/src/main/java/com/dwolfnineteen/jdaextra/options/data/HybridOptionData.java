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

public class HybridOptionData implements CommandOptionData, GeneralOptionData {
    // TODO: Maybe replace it with CommandOptionData?
    private final OptionData slashData;

    public HybridOptionData(@NotNull OptionType type, @NotNull String name, @NotNull String description) {
        slashData = new OptionData(type, name, description);
    }

    public HybridOptionData(@NotNull OptionType type,
                            @NotNull String name,
                            @NotNull String description,
                            boolean isRequired) {
        slashData = new OptionData(type, name, description, isRequired);
    }

    public HybridOptionData(@NotNull OptionType type,
                            @NotNull String name,
                            @NotNull String description,
                            boolean isRequired,
                            boolean isAutoComplete) {
        slashData = new OptionData(type, name, description, isRequired, isAutoComplete);
    }

    @Override
    @NotNull
    public OptionType getType() {
        return slashData.getType();
    }

    @Override
    @NotNull
    public String getName() {
        return slashData.getName();
    }

    @Override
    @NotNull
    public String getDescription() {
        return slashData.getDescription();
    }

    @Override
    public boolean isRequired() {
        return slashData.isRequired();
    }

    @Override
    public boolean isAutoComplete() {
        return slashData.isAutoComplete();
    }

    @Override
    @Nullable
    public Number getMinValue() {
        return slashData.getMinValue();
    }

    @Override
    @Nullable
    public Number getMaxValue() {
        return slashData.getMaxValue();
    }

    @Override
    @Nullable
    public Integer getMinLength() {
        return slashData.getMinLength();
    }

    @Override
    @Nullable
    public Integer getMaxLength() {
        return slashData.getMaxLength();
    }

    @Override
    @NotNull
    public HybridOptionData setName(@NotNull String name) {
        slashData.setName(name);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setDescription(@NotNull String description) {
        slashData.setDescription(description);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setRequired(boolean required) {
        slashData.setRequired(required);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setAutoComplete(boolean autoComplete) {
        slashData.setAutoComplete(autoComplete);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setMinValue(long minValue) {
        slashData.setMinValue(minValue);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setMinValue(double minValue) {
        slashData.setMinValue(minValue);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setMaxValue(long maxValue) {
        slashData.setMaxValue(maxValue);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setMaxValue(double maxValue) {
        slashData.setMaxValue(maxValue);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setRequiredRange(long minValue, long maxValue) {
        slashData.setRequiredRange(minValue, maxValue);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setRequiredRange(double minValue, double maxValue) {
        slashData.setRequiredRange(minValue, maxValue);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setMinLength(int minLength) {
        slashData.setMinLength(minLength);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setMaxLength(int maxLength) {
        slashData.setMaxLength(maxLength);

        return this;
    }

    @Override
    @NotNull
    public HybridOptionData setRequiredLength(int minLength, int maxLength) {
        slashData.setRequiredLength(minLength, maxLength);

        return this;
    }

    @Override
    public HybridOptionData addChoice(@NotNull String name, double value) {
        slashData.addChoice(name, value);

        return this;
    }

    @Override
    public HybridOptionData addChoice(@NotNull String name, long value) {
        slashData.addChoice(name, value);

        return this;
    }

    @Override
    public HybridOptionData addChoice(@NotNull String name, @NotNull String value) {
        slashData.addChoice(name, value);

        return this;
    }

    @Override
    public HybridOptionData addChoices(Command.@NotNull Choice... choices) {
        slashData.addChoices(choices);

        return this;
    }

    @Override
    public HybridOptionData addChoices(@NotNull Collection<? extends Command.Choice> choices) {
        slashData.addChoices(choices);

        return this;
    }

    @Override
    public OptionData toGeneralOptionData() {
        return slashData;
    }
}
