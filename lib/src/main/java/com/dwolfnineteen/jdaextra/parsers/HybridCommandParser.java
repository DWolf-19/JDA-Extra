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
import com.dwolfnineteen.jdaextra.events.HybridCommandEvent;
import com.dwolfnineteen.jdaextra.events.PrefixCommandEvent;
import com.dwolfnineteen.jdaextra.events.SlashCommandEvent;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.HybridCommandModel;
import com.dwolfnineteen.jdaextra.options.data.HybridOptionData;
import com.dwolfnineteen.jdaextra.options.mappings.HybridOptionMapping;
import com.dwolfnineteen.jdaextra.options.mappings.PrefixOptionMapping;
import com.dwolfnineteen.jdaextra.options.mappings.SlashOptionMapping;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parser for hybrid commands.
 *
 * @see com.dwolfnineteen.jdaextra.parsers parsers
 */
public class HybridCommandParser extends CommandParser {
    private final CommandParser parser;

    private HybridCommandModel model;

    /**
     * Construct new {@link com.dwolfnineteen.jdaextra.parsers.HybridCommandParser HybridCommandParser}.
     *
     * @param jdaExtra The {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra} instance.
     * @param parser The {@link com.dwolfnineteen.jdaextra.parsers.CommandParser CommandParser} to be used.
     */
    public HybridCommandParser(@NotNull JDAExtra jdaExtra,
                               @NotNull CommandParser parser) {
        this.jdaExtra = jdaExtra;
        this.parser = parser;
    }

    @Override
    @NotNull
    public GenericEvent getSourceEvent() {
        return parser.getSourceEvent();
    }

    /**
     * The {@link com.dwolfnineteen.jdaextra.models.HybridCommandModel HybridCommandModel} for this parser.
     *
     * @return The {@link com.dwolfnineteen.jdaextra.models.HybridCommandModel HybridCommandModel} for this parser.
     */
    @Override
    @Nullable
    public HybridCommandModel getModel() {
        return model;
    }

    /**
     * Sets {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} for this parser.
     *
     * @param model {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel}.
     * @return Current {@link com.dwolfnineteen.jdaextra.parsers.HybridCommandParser HybridCommandParser} instance,
     * for chaining.
     */
    @Override
    @NotNull
    public HybridCommandParser setModel(CommandModel model) {
        this.model = (HybridCommandModel) model;

        return this;
    }

    @Override
    @NotNull
    public Object[] buildInvokeArguments() {
        List<Object> arguments = new ArrayList<>();
        List<HybridOptionData> options = model.getOptions();

        // TODO: Remove duplicated code (for (int i = 0; i < options.size(); i++) {}...)
        if (parser instanceof PrefixCommandParser) {
            PrefixCommandParser prefixParser = (PrefixCommandParser) parser;

            List<PrefixOptionMapping> mappings = new ArrayList<>();

            String trigger = prefixParser.parseTrigger();

            for (int i = 0; i < prefixParser.parseOptions(trigger).length; i++) {
                mappings.add(new PrefixOptionMapping(options.get(i).getType(),
                        options.get(i).getName(),
                        prefixParser.parseOptions(trigger)[i],
                        prefixParser.getSourceEvent()));
            }

            for (int i = 0; i < options.size(); i++) {
                if (mappings.size() <= i) {
                    arguments.add(null);

                    continue;
                }

                arguments.add(buildInvokeArgumentType(options.get(i).getType(),
                        new HybridOptionMapping(mappings.get(i))));
            }

            arguments.add(0, new HybridCommandEvent(new PrefixCommandEvent(prefixParser.getSourceEvent(),
                    jdaExtra,
                    trigger,
                    prefixParser.parseName(trigger),
                    model.getDescription(),
                    mappings)));
        } else if (parser instanceof SlashCommandParser) {
            SlashCommandParser slashParser = (SlashCommandParser) parser;

            SlashCommandEvent slashEvent =
                    new SlashCommandEvent((SlashCommandInteractionEvent) slashParser.getSourceEvent(), jdaExtra);

            // TODO: See todo in SlashCommandEvent
            List<SlashOptionMapping> mappings = slashEvent.getOptions()
                    .stream()
                    .map(SlashOptionMapping::new)
                    .collect(Collectors.toList());

            for (int i = 0; i < options.size(); i++) {
                if (mappings.size() <= i) {
                    arguments.add(null);

                    continue;
                }

                arguments.add(buildInvokeArgumentType(options.get(i).getType(),
                        new HybridOptionMapping(mappings.get(i))));
            }

            arguments.add(0, new HybridCommandEvent(slashEvent));
        }

        return arguments.toArray();
    }
}
