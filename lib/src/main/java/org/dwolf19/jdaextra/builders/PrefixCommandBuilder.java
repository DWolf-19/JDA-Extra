package org.dwolf19.jdaextra.builders;

import org.dwolf19.jdaextra.annotations.ExtraMainCommand;
import org.dwolf19.jdaextra.annotations.ExtraPrefixCommand;
import org.dwolf19.jdaextra.commands.Command;
import org.dwolf19.jdaextra.commands.PrefixCommand;
import org.dwolf19.jdaextra.exceptions.CommandAnnotationNotFoundException;
import org.dwolf19.jdaextra.models.PrefixCommandModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public class PrefixCommandBuilder extends CommandBuilder {
    public PrefixCommandBuilder(@NotNull PrefixCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public PrefixCommandModel buildModel() {
        PrefixCommandModel model = new PrefixCommandModel();
        Class<? extends Command> commandClass = command.getClass();

        model.setCommand(command);

        for (Method method : commandClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExtraMainCommand.class)) {
                model.setMain(method);

                break;
            }
        }

        if (commandClass.isAnnotationPresent(ExtraPrefixCommand.class)) {
            ExtraPrefixCommand classAnnotation = commandClass.getAnnotation(ExtraPrefixCommand.class);

            model.setName(classAnnotation.name().isEmpty() ? model.getMain().getName() : classAnnotation.name());
            model.setDescription(classAnnotation.description().isEmpty() ? null : classAnnotation.description());
        } else
            throw new CommandAnnotationNotFoundException();

        return model;
    }
}
