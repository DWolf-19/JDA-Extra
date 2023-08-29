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

public interface CommandOptionData {
    OptionType getType();
    String getName();
    String getDescription();
    boolean isRequired();
    boolean isAutoComplete();
    Number getMinValue();
    Number getMaxValue();
    Integer getMinLength();
    Integer getMaxLength();

    CommandOptionData setName(String name);
    CommandOptionData setDescription(String description);
    CommandOptionData setRequired(boolean required);
    CommandOptionData setAutoComplete(boolean autoComplete);
    CommandOptionData setMinValue(Number minValue);
    CommandOptionData setMaxValue(Number maxValue);
    CommandOptionData setMinLength(int minLength);
    CommandOptionData setMaxLength(Integer maxLength);
}
