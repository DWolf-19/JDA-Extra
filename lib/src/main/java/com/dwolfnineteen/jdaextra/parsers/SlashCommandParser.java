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
import com.dwolfnineteen.jdaextra.events.SlashCommandEvent;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.SlashCommandModel;
import com.dwolfnineteen.jdaextra.options.data.SlashOptionData;
import com.dwolfnineteen.jdaextra.options.mappings.SlashOptionMapping;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parser for slash commands.
 * @see com.dwolfnineteen.jdaextra.parsers parsers
 */
public class SlashCommandParser extends CommandParser {
    private final SlashCommandInteractionEvent sourceEvent;

    private SlashCommandModel model;

    /**
     * Construct new {@link com.dwolfnineteen.jdaextra.parsers.SlashCommandParser SlashCommandParser}.
     *
     * @param jdaExtra The {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra} instance.
     * @param sourceEvent The {@link net.dv8tion.jda.api.events.GenericEvent GenericEvent} for this parser.
     */
    public SlashCommandParser(@NotNull JDAExtra jdaExtra,
                              @NotNull SlashCommandInteractionEvent sourceEvent) {
        this.jdaExtra = jdaExtra;
        this.sourceEvent = sourceEvent;
    }

    // TODO: SlashCommandParser#getSourceEvent return SlashCommandInteractionEvent
    /**
     * The {@link net.dv8tion.jda.api.events.GenericEvent GenericEvent} (source event) for this parser.
     *
     * @return The {@link net.dv8tion.jda.api.events.GenericEvent GenericEvent}.
     */
    @Override
    @NotNull
    public GenericEvent getSourceEvent() {
        return sourceEvent;
    }

    /**
     * The {@link com.dwolfnineteen.jdaextra.models.SlashCommandModel SlashCommandModel} for this parser.
     *
     * @return The {@link com.dwolfnineteen.jdaextra.models.SlashCommandModel SlashCommandModel}.
     * {@code null}, if the {@link com.dwolfnineteen.jdaextra.models.SlashCommandModel SlashCommandModel} not set.
     */
    @Override
    @Nullable
    public SlashCommandModel getModel() {
        return model;
    }

    /**
     * Sets {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} for this parser.
     *
     * @param model {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel}.
     * @return Current {@link com.dwolfnineteen.jdaextra.parsers.SlashCommandParser SlashCommandParser} instance,
     * for chaining.
     */
    @Override
    @NotNull
    public SlashCommandParser setModel(CommandModel model) {
        this.model = (SlashCommandModel) model;

        return this;
    }

    @Override
    @NotNull
    public Object[] buildInvokeArguments() {
        List<Object> arguments = new ArrayList<>();
        List<SlashOptionData> options = model.getOptions();

        List<OptionMapping> optionMappings = sourceEvent.getOptions();

        for (int i = 0; i < options.size(); i++) {
            if (optionMappings.size() <= i) {
                arguments.add(null);

                continue;
            }

            Object type = buildInvokeArgumentType(options.get(i).getType(),
                    new SlashOptionMapping(optionMappings.get(i)));
            arguments.add(type == null ? optionMappings.get(i).getAsAttachment() : type);
        }

        arguments.add(0, new SlashCommandEvent(sourceEvent, jdaExtra, optionMappings.stream()
                .map(SlashOptionMapping::new)
                .collect(Collectors.toList()), model.getDescription()));

        return arguments.toArray();
    }
}
