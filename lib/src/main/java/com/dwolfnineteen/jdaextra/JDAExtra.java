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
package com.dwolfnineteen.jdaextra;

import com.dwolfnineteen.jdaextra.builders.HybridCommandBuilder;
import com.dwolfnineteen.jdaextra.builders.PrefixCommandBuilder;
import com.dwolfnineteen.jdaextra.builders.SlashCommandBuilder;
import com.dwolfnineteen.jdaextra.commands.HybridCommand;
import com.dwolfnineteen.jdaextra.commands.PrefixCommand;
import com.dwolfnineteen.jdaextra.commands.SlashCommand;
import com.dwolfnineteen.jdaextra.events.HybridCommandEvent;
import com.dwolfnineteen.jdaextra.events.SlashCommandEvent;
import com.dwolfnineteen.jdaextra.exceptions.CommandNotFoundException;
import com.dwolfnineteen.jdaextra.models.HybridCommandModel;
import com.dwolfnineteen.jdaextra.models.PrefixCommandModel;
import com.dwolfnineteen.jdaextra.models.SlashCommandModel;
import com.dwolfnineteen.jdaextra.options.data.HybridOptionData;
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
import java.util.stream.Collectors;

public class JDAExtra extends ListenerAdapter {
    private final String prefix;
    private final boolean whenMention;

    private final HashMap<String, HybridCommandModel> hybridCommandsModels;
    private final HashMap<String, PrefixCommandModel> prefixCommandsModels;
    private final HashMap<String, SlashCommandModel> slashCommandsModels;

    public JDAExtra(@NotNull String prefix,
                    boolean whenMention,
                    @NotNull ArrayList<HybridCommand> hybridCommands,
                    @NotNull ArrayList<PrefixCommand> prefixCommands,
                    @NotNull ArrayList<SlashCommand> slashCommands) {
        this.prefix = prefix;
        this.whenMention = whenMention;

        HashMap<String, HybridCommandModel> hybridCommandModels = new HashMap<>();

        for (HybridCommand hybridCommand : hybridCommands) {
            HybridCommandModel hybridModel = new HybridCommandBuilder(hybridCommand).buildModel();
            hybridCommandModels.put(hybridModel.getName(), hybridModel);
        }

        HashMap<String, PrefixCommandModel> prefixCommandModels = new HashMap<>();

        for (PrefixCommand prefixCommand : prefixCommands) {
            PrefixCommandModel prefixModel = new PrefixCommandBuilder(prefixCommand).buildModel();
            prefixCommandModels.put(prefixModel.getName(), prefixModel);
        }

        HashMap<String, SlashCommandModel> slashCommandModels = new HashMap<>();

        for (SlashCommand slashCommand : slashCommands) {
            SlashCommandModel slashModel = new SlashCommandBuilder(slashCommand).buildModel();
            slashCommandModels.put(slashModel.getName(), slashModel);
        }

        this.hybridCommandsModels = hybridCommandModels;
        this.prefixCommandsModels = prefixCommandModels;
        this.slashCommandsModels = slashCommandModels;
    }

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    public boolean isWhenMention() {
        return whenMention;
    }

    @NotNull
    public HashMap<String, HybridCommandModel> getHybridCommandsModels() {
        return hybridCommandsModels;
    }

    @NotNull
    public HashMap<String, PrefixCommandModel> getPrefixCommandsModels() {
        return prefixCommandsModels;
    }

    @NotNull
    public HashMap<String, SlashCommandModel> getSlashCommandsModels() {
        return slashCommandsModels;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        ArrayList<CommandData> data = new ArrayList<>();

        // TODO: Add SlashCommandData#setDefaultPermissions
        for (HybridCommandModel command : hybridCommandsModels.values()) {
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .addOptions(command.getOptions()
                            .stream()
                            .map(HybridOptionData::toGeneralOptionData)
                            .collect(Collectors.toList()))
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));
        }

        for (SlashCommandModel command : slashCommandsModels.values()) {
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .addOptions(command.getOptions())
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));
        }

        event.getJDA().updateCommands().addCommands(data).queue();
    }

    private void onHybridCommand(@NotNull String commandName, @NotNull PrefixCommandParser parser) {
        HybridCommandModel hybridModel = hybridCommandsModels.get(commandName);

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

    private void onHybridCommand(@NotNull String commandName, @NotNull SlashCommandParser parser) {
        HybridCommandModel hybridModel = hybridCommandsModels.get(commandName);

        if (hybridModel == null) {
            throw new CommandNotFoundException(commandName);
        }

        HybridCommandParser hybridParser =
                new HybridCommandParser(this, parser).setModel(hybridModel);

        try {
            // TODO: Fix "Method invocation 'invoke' may produce 'NullPointerException'"
            //  by adding subcommands(group) support
            hybridModel.getMain().invoke(hybridModel.getCommand(), hybridParser.buildInvokeArguments()); // Done
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        PrefixCommandParser parser = new PrefixCommandParser(this, event);

        if (!parser.isCommand()) {
            return; // It's just a message
        }

        String commandName = parser.parseName(parser.parseTrigger());

        PrefixCommandModel prefixModel = prefixCommandsModels.get(commandName);

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

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        SlashCommandModel slashModel = slashCommandsModels.get(event.getName());

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
