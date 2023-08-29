/*
Copyright (c) 2023 DWolf Nineteen & The JDA-Extra contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.dwolfnineteen.jdaextra.options.data;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrefixOptionData implements CommandOptionData {
    private final OptionType type;
    private String name;
    private String description;
    private boolean isRequired;
    private boolean isAutoComplete;
    private Number minValue;
    private Number maxValue;
    private int minLength;
    private Integer maxLength;

    public PrefixOptionData(@NotNull OptionType type, @NotNull String name) {
        this.type = type;
        this.name = name;
    }

    public PrefixOptionData(@NotNull OptionType type, @NotNull String name, @Nullable String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public PrefixOptionData(@NotNull OptionType type, @NotNull String name, boolean isRequired) {
        this.type = type;
        this.name = name;
        this.isRequired = isRequired;
    }

    public PrefixOptionData(@NotNull OptionType type,
                            @NotNull String name,
                            @Nullable String description,
                            boolean isRequired) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
    }

    public PrefixOptionData(@NotNull OptionType type,
                            @NotNull String name,
                            boolean isRequired,
                            boolean isAutoComplete) {
        this.type = type;
        this.name = name;
        this.isRequired = isRequired;
        this.isAutoComplete = isAutoComplete;
    }

    public PrefixOptionData(@NotNull OptionType type,
                            @NotNull String name,
                            @Nullable String description,
                            boolean isRequired,
                            boolean isAutoComplete) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
        this.isAutoComplete = isAutoComplete;
    }

    @Override
    @NotNull
    public OptionType getType() {
        return type;
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    @Nullable
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    @Override
    public boolean isAutoComplete() {
        return isAutoComplete;
    }

    @Override
    @Nullable
    public Number getMinValue() {
        return minValue;
    }

    @Override
    @Nullable
    public Number getMaxValue() {
        return maxValue;
    }

    @Override
    @Nullable
    public Integer getMinLength() {
        return minLength;
    }

    @Override
    @Nullable
    public Integer getMaxLength() {
        return maxLength;
    }

    @Override
    @NotNull
    public PrefixOptionData setName(@NotNull String name) {
        this.name = name;

        return this;
    }

    @Override
    @NotNull
    public PrefixOptionData setDescription(@NotNull String description) {
        this.description = description;

        return this;
    }

    @Override
    @NotNull
    public PrefixOptionData setRequired(boolean required) {
        isRequired = required;

        return this;
    }

    @Override
    @NotNull
    public PrefixOptionData setAutoComplete(boolean autoComplete) {
        isAutoComplete = autoComplete;

        return this;
    }

    @Override
    @NotNull
    public PrefixOptionData setMinValue(Number minValue) {
        this.minValue = minValue;

        return this;
    }

    @Override
    @NotNull
    public PrefixOptionData setMaxValue(Number maxValue) {
        this.maxValue = maxValue;

        return this;
    }

    @Override
    @NotNull
    public PrefixOptionData setMinLength(int minLength) {
        this.minLength = minLength;

        return this;
    }

    @Override
    @NotNull
    public PrefixOptionData setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;

        return this;
    }
}
