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
package com.dwolfnineteen.jdaextra.prefix;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrefixOptionData {
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

    @NotNull
    public OptionType getType() {
        return type;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean isAutoComplete() {
        return isAutoComplete;
    }

    @Nullable
    public Number getMinValue() {
        return minValue;
    }

    @Nullable
    public Number getMaxValue() {
        return maxValue;
    }

    @Nullable
    public Integer getMinLength() {
        return minLength;
    }

    @Nullable
    public Integer getMaxLength() {
        return maxLength;
    }

    @NotNull
    public PrefixOptionData setName(@NotNull String name) {
        this.name = name;

        return this;
    }

    @NotNull
    public PrefixOptionData setDescription(@NotNull String description) {
        this.description = description;

        return this;
    }

    @NotNull
    public PrefixOptionData setRequired(boolean required) {
        isRequired = required;

        return this;
    }

    @NotNull
    public PrefixOptionData setAutoComplete(boolean autoComplete) {
        isAutoComplete = autoComplete;

        return this;
    }

    @NotNull
    public PrefixOptionData setMinValue(Number minValue) {
        this.minValue = minValue;

        return this;
    }

    @NotNull
    public PrefixOptionData setMaxValue(Number maxValue) {
        this.maxValue = maxValue;

        return this;
    }

    @NotNull
    public PrefixOptionData setMinLength(int minLength) {
        this.minLength = minLength;

        return this;
    }

    @NotNull
    public PrefixOptionData setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;

        return this;
    }
}
