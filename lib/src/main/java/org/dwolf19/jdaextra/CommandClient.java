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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandClient extends ListenerAdapter {
    private final String prefix;
    private final boolean whenMention;

    private final HashMap<String, HybridCommand> hybridCommands;
    private final HashMap<String, PrefixCommand> prefixCommands;
    private final HashMap<String, SlashCommand> slashCommands;

    public CommandClient(@NotNull String prefix,
                         boolean whenMention,
                         @NotNull HashMap<String, HybridCommand> hybridCommands,
                         @NotNull HashMap<String, PrefixCommand> prefixCommands,
                         @NotNull HashMap<String, SlashCommand> slashCommands) {
        this.prefix = prefix;
        this.whenMention = whenMention;

        this.hybridCommands = hybridCommands;
        this.prefixCommands = prefixCommands;
        this.slashCommands = slashCommands;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        ArrayList<SlashCommandData> data = new ArrayList<>();

        for (HybridCommand command : hybridCommands.values())
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .setDefaultPermissions(command.getDefaultMemberPermissions())
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));

        for (SlashCommand command : slashCommands.values())
            data.add(Commands.slash(command.getName(), command.getDescription())
                    .setDefaultPermissions(command.getDefaultMemberPermissions())
                    .setGuildOnly(command.isGuildOnly())
                    .setNSFW(command.isNSFW()));

        event.getJDA().updateCommands().addCommands(data).queue();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // TODO: add logic
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // TODO: add logic
    }
}
