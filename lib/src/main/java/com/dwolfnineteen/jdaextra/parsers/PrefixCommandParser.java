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
import com.dwolfnineteen.jdaextra.events.PrefixCommandEvent;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.PrefixCommandModel;
import com.dwolfnineteen.jdaextra.options.data.PrefixOptionData;
import com.dwolfnineteen.jdaextra.options.mappings.PrefixOptionMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Add "caching" for PrefixCommandParser#parse** methods
/**
 * Parser for prefix commands.
 * @see com.dwolfnineteen.jdaextra.parsers parsers
 */
public class PrefixCommandParser extends CommandParser {
    private final MessageReceivedEvent sourceEvent;

    private final String content;
    private final String prefix;
    private final String mention;

    private PrefixCommandModel model;

    /**
     * Construct new {@link com.dwolfnineteen.jdaextra.parsers.PrefixCommandParser PrefixCommandParser}.
     *
     * @param jdaExtra The {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra} instance.
     * @param sourceEvent The {@link net.dv8tion.jda.api.events.GenericEvent GenericEvent} for this parser.
     */
    public PrefixCommandParser(@NotNull JDAExtra jdaExtra,
                               @NotNull MessageReceivedEvent sourceEvent) {
        this.jdaExtra = jdaExtra;
        this.sourceEvent = sourceEvent;

        this.content = sourceEvent.getMessage().getContentRaw();
        this.prefix = jdaExtra.getPrefix();
        this.mention = sourceEvent.getJDA().getSelfUser().getAsMention();
    }

    /**
     * The {@link net.dv8tion.jda.api.events.message.MessageReceivedEvent MessageReceivedEvent} (source event)
     * for this parser.
     *
     * @return The {@link net.dv8tion.jda.api.events.message.MessageReceivedEvent MessageReceivedEvent}.
     */
    @Override
    @NotNull
    public MessageReceivedEvent getSourceEvent() {
        return sourceEvent;
    }

    /**
     * The {@link com.dwolfnineteen.jdaextra.models.PrefixCommandModel PrefixCommandModel} for this parser.
     *
     * @return The {@link com.dwolfnineteen.jdaextra.models.PrefixCommandModel PrefixCommandModel}.
     */
    @Override
    @NotNull
    public PrefixCommandModel getModel() {
        return model;
    }

    /**
     * Set {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} for this parser.
     *
     * @param model {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel}.
     * @return Current {@link com.dwolfnineteen.jdaextra.parsers.PrefixCommandParser PrefixCommandParser} instance,
     * for chaining.
     */
    @Override
    @NotNull
    public PrefixCommandParser setModel(@NotNull CommandModel model) {
        this.model = (PrefixCommandModel) model;

        return this;
    }

    @Override
    public Object[] buildInvokeArguments() {
        if (model == null) {
            throw new NullPointerException(); // Please set model before!
        }

        List<Object> arguments = new ArrayList<>();
        List<PrefixOptionData> options = model.getOptions();

        List<PrefixOptionMapping> mappings = buildOptionsMappings(parseOptions(parseTrigger()));

        for (int i = 0; i < options.size(); i++) {
            if (mappings.size() <= i) {
                arguments.add(null);

                continue;
            }

            arguments.add(buildInvokeArgumentType(options.get(i).getType(), mappings.get(i)));
        }

        arguments.add(0, new PrefixCommandEvent(sourceEvent,
                jdaExtra,
                parseTrigger(),
                parseName(parseTrigger()),
                model.getDescription(),
                mappings));

        return arguments.toArray();
    }

    /**
     * Checks if the source message begins with a given prefix.
     *
     * @return {@code True}, if it is a command.
     */
    public boolean isCommand() {
        return content.startsWith(prefix) || (jdaExtra.isWhenMention() && content.startsWith(mention));
    }

    /**
     * Trigger (prefix/mention) from the message.
     *
     * @return Trigger for this command.
     * @throws NullPointerException if the message not a command ({@link #isCommand() isCommand()} is {@code False}).
     */
    @NotNull
    public String parseTrigger() {
        if (!isCommand()) {
            throw new NullPointerException();
        }

        return content.startsWith(prefix) ? prefix : mention + " ";
    }

    /**
     * Command name from the message.
     *
     * @param trigger Trigger for this command.
     * @return The command name.
     * @throws NullPointerException if the message not a command ({@link #isCommand() isCommand()} is {@code False}).
     */
    @NotNull
    public String parseName(@NotNull String trigger) {
        if (!isCommand()) {
            throw new NullPointerException();
        }

        return content.substring(trigger.length()).split(" ")[0];
    }

    /**
     * Command options values as a {@link String String} array.
     *
     * @param trigger Trigger for this command.
     * @return Options values.
     * @throws NullPointerException if the message not a command ({@link #isCommand() isCommand()} is {@code False}).
     */
    @NotNull
    public String[] parseOptions(@NotNull String trigger) {
        if (!isCommand()) {
            throw new NullPointerException();
        }

        String[] parts = content.substring(trigger.length()).split(" ");

        return Arrays.copyOfRange(parts, 1, parts.length);
    }

    @NotNull
    private List<PrefixOptionMapping> buildOptionsMappings(@NotNull String[] options) {
        if (model == null) {
            throw new NullPointerException();
        }

        List<PrefixOptionMapping> mappings = new ArrayList<>();

        for (int i = 0; i < options.length; i++) {
            PrefixOptionData data = model.getOptions().get(i);

            mappings.add(new PrefixOptionMapping(data.getType(),
                    data.getName(),
                    options[i],
                    sourceEvent));
        }

        return mappings;
    }
}
