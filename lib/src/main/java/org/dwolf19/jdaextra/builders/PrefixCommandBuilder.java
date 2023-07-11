package org.dwolf19.jdaextra.builders;

import org.dwolf19.jdaextra.annotations.commands.ExtraCommand;
import org.dwolf19.jdaextra.annotations.commands.ExtraMainCommand;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.models.PrefixCommandModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public class PrefixCommandBuilder implements CommandBuilder {
    private final PrefixCommand command;

    public PrefixCommandBuilder(@NotNull PrefixCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public PrefixCommandModel buildModel() {
        PrefixCommandModel model = new PrefixCommandModel();

        return model;
    }
}
