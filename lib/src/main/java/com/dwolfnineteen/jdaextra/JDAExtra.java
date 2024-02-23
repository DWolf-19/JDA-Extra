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
package com.dwolfnineteen.jdaextra;

import com.dwolfnineteen.jdaextra.builders.HybridCommandBuilder;
import com.dwolfnineteen.jdaextra.builders.PrefixCommandBuilder;
import com.dwolfnineteen.jdaextra.builders.SlashCommandBuilder;
import com.dwolfnineteen.jdaextra.commands.HybridCommand;
import com.dwolfnineteen.jdaextra.commands.PrefixCommand;
import com.dwolfnineteen.jdaextra.commands.SlashCommand;
import com.dwolfnineteen.jdaextra.exceptions.CommandNotFoundException;
import com.dwolfnineteen.jdaextra.models.HybridCommandModel;
import com.dwolfnineteen.jdaextra.models.PrefixCommandModel;
import com.dwolfnineteen.jdaextra.models.SlashCommandModel;
import com.dwolfnineteen.jdaextra.options.data.HybridOptionData;
import com.dwolfnineteen.jdaextra.options.data.SlashOptionData;
import com.dwolfnineteen.jdaextra.parsers.CommandParser;
import com.dwolfnineteen.jdaextra.parsers.HybridCommandParser;
import com.dwolfnineteen.jdaextra.parsers.PrefixCommandParser;
import com.dwolfnineteen.jdaextra.parsers.SlashCommandParser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* TODO: Think about
    @NotNull
    public String getSomething() {
        return something;
    }
    or
    public @NotNull String getSomething() {
        return something;
    } ?
*/
/**
 * The main class, event interceptor of JDA-Extra.
 * <br>
 * This class is inherited from {@link net.dv8tion.jda.api.hooks.ListenerAdapter ListenerAdapter},
 * it uses events for building application commands data for {@link net.dv8tion.jda.api.JDA JDA}
 * and commands parsing when calling event handlers.
 *
 * @see com.dwolfnineteen.jdaextra.JDAExtraBuilder JDAExtraBuilder
 */
public class JDAExtra extends ListenerAdapter {
    private final String prefix;
    private final boolean whenMention;

    private final Map<String, HybridCommandModel> hybridCommandModels;
    private final Map<String, PrefixCommandModel> prefixCommandModels;
    private final Map<String, SlashCommandModel> slashCommandModels;

    /**
     * Build new {@link JDAExtra} instance (usually called from {@link JDAExtraBuilder}).
     *
     * @param prefix The prefix for prefix/hybrid commands.
     * @param whenMention Whether bot react to its mention as a prefix.
     * @param hybridCommands List of hybrid commands classes.
     * @param prefixCommands List of prefix commands classes.
     * @param slashCommands List of slash commands classes.
     */
    public JDAExtra(@NotNull String prefix,
                    boolean whenMention,
                    @NotNull List<HybridCommand> hybridCommands,
                    @NotNull List<PrefixCommand> prefixCommands,
                    @NotNull List<SlashCommand> slashCommands) {
        this.prefix = prefix;
        this.whenMention = whenMention;

        Map<String, HybridCommandModel> hybridCommandModels = new HashMap<>();

        for (HybridCommand hybridCommand : hybridCommands) {
            HybridCommandModel hybridModel = new HybridCommandBuilder(hybridCommand).buildModel();
            hybridCommandModels.put(hybridModel.getName(), hybridModel);
        }

        Map<String, PrefixCommandModel> prefixCommandModels = new HashMap<>();

        for (PrefixCommand prefixCommand : prefixCommands) {
            PrefixCommandModel prefixModel = new PrefixCommandBuilder(prefixCommand).buildModel();
            prefixCommandModels.put(prefixModel.getName(), prefixModel);
        }

        Map<String, SlashCommandModel> slashCommandModels = new HashMap<>();

        for (SlashCommand slashCommand : slashCommands) {
            SlashCommandModel slashModel = new SlashCommandBuilder(slashCommand).buildModel();
            slashCommandModels.put(slashModel.getName(), slashModel);
        }

        this.hybridCommandModels = hybridCommandModels;
        this.prefixCommandModels = prefixCommandModels;
        this.slashCommandModels = slashCommandModels;
    }

    /**
     * The prefix for prefix/hybrid commands.
     *
     * @return The prefix.
     */
    @NotNull
    public String getPrefix() {
        return prefix;
    }

    /**
     * Whether bot will react to its mention as a prefix.
     *
     * @return {@code True}, if bot will react to its mention as a prefix.
     */
    public boolean isWhenMention() {
        return whenMention;
    }

    /**
     * Map of command name and {@link HybridCommandModel}.
     *
     * @return Map of command name and {@link com.dwolfnineteen.jdaextra.models.HybridCommandModel HybridCommandModel}.
     * @see com.dwolfnineteen.jdaextra.builders builders
     */
    @NotNull
    public Map<String, HybridCommandModel> getHybridCommandModels() {
        return hybridCommandModels;
    }

    /**
     * Map of command name and {@link com.dwolfnineteen.jdaextra.models.PrefixCommandModel PrefixCommandModel}.
     *
     * @return Map of command name and {@link com.dwolfnineteen.jdaextra.models.PrefixCommandModel}.
     * @see com.dwolfnineteen.jdaextra.builders builders
     */
    @NotNull
    public Map<String, PrefixCommandModel> getPrefixCommandModels() {
        return prefixCommandModels;
    }

    /**
     * Map of command name and {@link com.dwolfnineteen.jdaextra.models.SlashCommandModel SlashCommandModel}.
     *
     * @return Map of command name and {@link com.dwolfnineteen.jdaextra.models.SlashCommandModel SlashCommandModel}.
     * @see com.dwolfnineteen.jdaextra.builders builders
     */
    @NotNull
    public Map<String, SlashCommandModel> getSlashCommandModels() {
        return slashCommandModels;
    }

    /**
     * {@link ReadyEvent} handler for adding application commands data to {@link net.dv8tion.jda.api.JDA JDA}.
     *
     * @param event The {@link ReadyEvent}.
     */
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> data = new ArrayList<>();

        // TODO: Add SlashCommandData#setDefaultPermissions
        for (HybridCommandModel command : hybridCommandModels.values()) {
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .setNameLocalizations(command.getNameLocalizations()) // <-----------------╮︎
                    .setDescriptionLocalizations(command.getDescriptionLocalizations()) // <---┤︎ Slash exclusive
                    .setLocalizationFunction(command.getLocalizationFunction()) // <-----------╯︎
                    .addOptions(command.getOptions()
                            .stream()
                            .map(HybridOptionData::toGeneralOptionData)
                            .collect(Collectors.toList()))
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));
        }

        for (SlashCommandModel command : slashCommandModels.values()) {
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .setNameLocalizations(command.getNameLocalizations())
                    .setDescriptionLocalizations(command.getDescriptionLocalizations())
                    .setLocalizationFunction(command.getLocalizationFunction())
                    .addOptions(command.getOptions()
                            .stream()
                            .map(SlashOptionData::toGeneralOptionData)
                            .collect(Collectors.toList()))
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));
        }

        event.getJDA().updateCommands().addCommands(data).queue();
    }

    private void onHybridCommand(@NotNull String commandName, @NotNull CommandParser parser) {
        HybridCommandModel hybridModel = hybridCommandModels.get(commandName);

        if (hybridModel == null) {
            throw new CommandNotFoundException(commandName);
        }

        HybridCommandParser hybridParser = new HybridCommandParser(this, parser).setModel(hybridModel);

        try {
            // TODO: Fix "Method invocation 'invoke' may produce 'NullPointerException'"
            //  by adding subcommands(group) support
            hybridModel.getMain().invoke(hybridModel.getCommand(), hybridParser.buildInvokeArguments()); // Done
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * {@link net.dv8tion.jda.api.events.message.MessageReceivedEvent MessageReceivedEvent} handler
     * for parsing prefix/hybrid commands and executing their logic.
     *
     * @param event The {@link net.dv8tion.jda.api.events.message.MessageReceivedEvent MessageReceivedEvent}.
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        PrefixCommandParser parser = new PrefixCommandParser(this, event);

        if (!parser.isCommand()) {
            return; // It's just a message
        }

        String commandName = parser.parseName(parser.parseTrigger());

        PrefixCommandModel prefixModel = prefixCommandModels.get(commandName);

        if (prefixModel == null) {
            onHybridCommand(commandName, parser);
        } else {
            parser.setModel(prefixModel);

            try {
                // TODO: Fix "Method invocation 'invoke' may produce 'NullPointerException'"
                //  by adding subcommands(group) support
                prefixModel.getMain().invoke(prefixModel.getCommand(), parser.buildInvokeArguments()); // Done
            } catch (IllegalAccessException | InvocationTargetException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * {@link net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent SlashCommandInteractionEvent}
     * handler for parsing slash commands and executing their logic.
     *
     * @param event The {@link net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent SlashCommandInteractionEvent}.
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        SlashCommandModel slashModel = slashCommandModels.get(event.getName());

        SlashCommandParser slashParser = new SlashCommandParser(this, event).setModel(slashModel);

        if (slashModel == null) {
            onHybridCommand(event.getName(), slashParser);
        } else {
            try {
                // TODO: Fix "Method invocation 'invoke' may produce 'NullPointerException'"
                //  by adding subcommands(group) support
                slashModel.getMain().invoke(slashModel.getCommand(), slashParser.buildInvokeArguments()); // Done
            } catch (IllegalAccessException | InvocationTargetException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
