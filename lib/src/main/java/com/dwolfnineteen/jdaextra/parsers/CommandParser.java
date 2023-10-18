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
package com.dwolfnineteen.jdaextra.parsers;

import com.dwolfnineteen.jdaextra.JDAExtra;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.PrefixCommandModel;
import com.dwolfnineteen.jdaextra.options.mappings.CommandOptionMapping;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class CommandParser {
    protected JDAExtra jdaExtra;

    public abstract Object[] buildInvokeArguments();

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }

    public abstract GenericEvent getSourceEvent();
    public abstract CommandModel getModel();

    public abstract CommandParser setModel(CommandModel model);

    @Nullable
    protected Object buildInvokeArgumentType(@NotNull OptionType type, @NotNull CommandOptionMapping mapping) {
        switch (type) {
            case BOOLEAN:
                return mapping.getAsBoolean();
            case CHANNEL:
                return mapping.getAsChannel();
            case INTEGER:
                return mapping.getAsLong();
            case MENTIONABLE:
                return mapping.getAsMentionable();
            case NUMBER:
                return mapping.getAsDouble();
            case STRING:
                return mapping.getAsString();
            case USER:
                return mapping.getAsUser();
            default:
                return null;
        }
    }
}
