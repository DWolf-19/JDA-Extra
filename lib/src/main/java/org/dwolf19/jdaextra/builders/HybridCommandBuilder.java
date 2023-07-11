package org.dwolf19.jdaextra.builders;

import org.dwolf19.jdaextra.commands.HybridCommand;
import org.dwolf19.jdaextra.models.HybridCommandModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HybridCommandBuilder implements CommandBuilder {
    private final HybridCommand command;

    public HybridCommandBuilder(@NotNull HybridCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public HybridCommandModel buildModel() {
        HybridCommandModel model = new HybridCommandModel();

        // TODO: add logic

        return model;
    }
}
