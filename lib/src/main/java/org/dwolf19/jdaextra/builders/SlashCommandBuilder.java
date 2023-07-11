package org.dwolf19.jdaextra.builders;

import org.dwolf19.jdaextra.commands.SlashCommand;

import org.dwolf19.jdaextra.models.SlashCommandModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlashCommandBuilder implements CommandBuilder {
    private final SlashCommand command;

    public SlashCommandBuilder(@NotNull SlashCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public SlashCommandModel buildModel() {
        SlashCommandModel model = new SlashCommandModel();

        // TODO: add logic

        return model;
    }
}
