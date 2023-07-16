package org.dwolf19.jdaextra.builders;

import org.dwolf19.jdaextra.annotations.ExtraPrefixCommand;
import org.dwolf19.jdaextra.annotations.ExtraMainCommand;
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

        model.setCommand(command);

        for (Method method : command.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExtraMainCommand.class)) {
                model.setMain(method);

                if (command.getClass().isAnnotationPresent(ExtraPrefixCommand.class)) {
                    ExtraPrefixCommand classAnnotation = command.getClass().getAnnotation(ExtraPrefixCommand.class);

                    if (classAnnotation.name().isEmpty())
                        model.setName(method.getName());
                    else
                        model.setName(classAnnotation.name());
                }

                break;
            }
        }

        return model;
    }
}
