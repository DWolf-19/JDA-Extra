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
import com.dwolfnineteen.jdaextra.events.PrefixCommandEvent;
import com.dwolfnineteen.jdaextra.events.SlashCommandEvent;
import com.dwolfnineteen.jdaextra.exceptions.CommandNotFoundException;
import com.dwolfnineteen.jdaextra.models.HybridCommandModel;
import com.dwolfnineteen.jdaextra.models.PrefixCommandModel;
import com.dwolfnineteen.jdaextra.models.SlashCommandModel;
import com.dwolfnineteen.jdaextra.prefix.PrefixCommandEntity;
import com.dwolfnineteen.jdaextra.prefix.PrefixCommandParser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        ArrayList<SlashCommandData> data = new ArrayList<>();

        for (HybridCommandModel command : hybridCommandsModels.values())
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .addOptions(command.getOptions())
//                    .setDefaultPermissions(command.getDefaultMemberPermissions())
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));

        for (SlashCommandModel command : slashCommandsModels.values())
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .addOptions(command.getOptions())
//                    .setDefaultPermissions(command.getDefaultMemberPermissions())
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));

        event.getJDA().updateCommands().addCommands(data).queue();
    }

    private void onHybridCommand(@NotNull PrefixCommandParser parser) {
        PrefixCommandEntity entity = parser.getEntity();

        HybridCommandModel hybridModel = hybridCommandsModels.get(entity.getName());

        if (hybridModel == null)
            throw new CommandNotFoundException(entity.getName());

        PrefixCommandEvent prefixEvent = new PrefixCommandEvent(parser.getEvent(),
                this,
                entity.getTrigger(),
                hybridModel.getName(),
                hybridModel.getDescription());

        try {
            // Done
            hybridModel.getMain().invoke(hybridModel.getCommand(), new HybridCommandEvent(prefixEvent));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void onHybridCommand(@NotNull SlashCommandInteractionEvent event) {
        HybridCommandModel hybridModel = hybridCommandsModels.get(event.getName());

        if (hybridModel == null)
            throw new CommandNotFoundException(event.getName());

        SlashCommandEvent slashEvent = new SlashCommandEvent(event, this);

        try {
            // Done
            hybridModel.getMain().invoke(hybridModel.getCommand(), new HybridCommandEvent(slashEvent));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        PrefixCommandParser parser = new PrefixCommandParser(event, this).parse();

        if (parser == null)
            return; // It's just a message

        PrefixCommandEntity entity = parser.getEntity();

        PrefixCommandModel prefixModel = prefixCommandsModels.get(entity.getName());

        if (prefixModel == null) {
            onHybridCommand(parser);
        } else {
            PrefixCommandEvent prefixEvent = new PrefixCommandEvent(event,
                    this,
                    entity.getTrigger(),
                    entity.getName(),
                    prefixModel.getDescription());

            try {
                // Done
                prefixModel.getMain().invoke(prefixModel.getCommand(), prefixEvent);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        SlashCommandModel slashModel = slashCommandsModels.get(event.getName());

        if (slashModel == null) {
            onHybridCommand(event);
        } else {
            try {
                // Done
                slashModel.getMain().invoke(slashModel.getCommand(), new SlashCommandEvent(event, this));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
