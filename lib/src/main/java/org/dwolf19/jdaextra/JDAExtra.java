package org.dwolf19.jdaextra;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import org.dwolf19.jdaextra.commands.HybridCommand;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.commands.SlashCommand;
import org.dwolf19.jdaextra.events.PrefixCommandEvent;
import org.dwolf19.jdaextra.exceptions.CommandNotFoundException;
import org.dwolf19.jdaextra.models.PrefixCommandModel;
import org.dwolf19.jdaextra.parsers.PrefixCommandParser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class JDAExtra extends ListenerAdapter {
    private final String prefix;
    private final boolean whenMention;

    private final HashMap<String, HybridCommand> hybridCommands;
    private final HashMap<String, PrefixCommand> prefixCommands;
    private final HashMap<String, SlashCommand> slashCommands;

    public JDAExtra(
            @NotNull String prefix,
            boolean whenMention,
            @NotNull HashMap<String, HybridCommand> hybridCommands,
            @NotNull HashMap<String, PrefixCommand> prefixCommands,
            @NotNull HashMap<String, SlashCommand> slashCommands
    ) {
        this.prefix = prefix;
        this.whenMention = whenMention;

        this.hybridCommands = hybridCommands;
        this.prefixCommands = prefixCommands;
        this.slashCommands = slashCommands;
    }

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    public boolean isWhenMention() {
        return whenMention;
    }

    @NotNull
    public HashMap<String, HybridCommand> getHybridCommands() {
        return hybridCommands;
    }

    @NotNull
    public HashMap<String, PrefixCommand> getPrefixCommands() {
        return prefixCommands;
    }

    @NotNull
    public HashMap<String, SlashCommand> getSlashCommands() {
        return slashCommands;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        ArrayList<SlashCommandData> data = new ArrayList<>();

        for (HybridCommand command : hybridCommands.values())
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .addOptions(command.getOptions().build())
                    .setDefaultPermissions(command.getDefaultMemberPermissions())
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));

        for (SlashCommand command : slashCommands.values())
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .addOptions(command.getOptions().build())
                    .setDefaultPermissions(command.getDefaultMemberPermissions())
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));

        event.getJDA().updateCommands().addCommands(data).queue();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        PrefixCommandModel model = new PrefixCommandParser(event, this).buildModel();

        if (model == null) {
            return;  // It's just a message
        }

        PrefixCommand command = prefixCommands.get(model.getName());

        if (command == null) {
            throw new CommandNotFoundException(model.getName());
        }

        command.executePrefixLogic(new PrefixCommandEvent(event, this));  // Done
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // TODO: add logic
    }
}
