package org.dwolf19.jdaextra;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import org.dwolf19.jdaextra.builders.HybridCommandBuilder;
import org.dwolf19.jdaextra.builders.PrefixCommandBuilder;
import org.dwolf19.jdaextra.builders.SlashCommandBuilder;
import org.dwolf19.jdaextra.commands.HybridCommand;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.commands.SlashCommand;
import org.dwolf19.jdaextra.events.PrefixCommandEvent;
import org.dwolf19.jdaextra.exceptions.CommandNotFoundException;
import org.dwolf19.jdaextra.prefix.PrefixCommandEntity;
import org.dwolf19.jdaextra.models.HybridCommandModel;
import org.dwolf19.jdaextra.models.PrefixCommandModel;
import org.dwolf19.jdaextra.models.SlashCommandModel;
import org.dwolf19.jdaextra.prefix.PrefixCommandParser;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        PrefixCommandEntity entity = new PrefixCommandParser(event, this).buildEntity();

        if (entity == null) {
            return;  // It's just a message
        }

        PrefixCommandModel model = prefixCommandsModels.get(entity.getName());

        if (model == null) {
            throw new CommandNotFoundException(entity.getName());
        }

        try {
            model.getMain().invoke(model.getCommand(), new PrefixCommandEvent(event, this));  // Done
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // TODO: add logic
    }
}
