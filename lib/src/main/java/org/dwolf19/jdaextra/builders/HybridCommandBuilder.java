package org.dwolf19.jdaextra.builders;

import org.dwolf19.jdaextra.annotations.ExtraHybridCommand;
import org.dwolf19.jdaextra.annotations.ExtraMainCommand;
import org.dwolf19.jdaextra.commands.Command;
import org.dwolf19.jdaextra.commands.HybridCommand;
import org.dwolf19.jdaextra.exceptions.CommandAnnotationNotFoundException;
import org.dwolf19.jdaextra.models.HybridCommandModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public class HybridCommandBuilder extends CommandBuilder {
    public HybridCommandBuilder(@NotNull HybridCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public HybridCommandModel buildModel() {
        HybridCommandModel model = new HybridCommandModel();
        Class<? extends Command> commandClass = command.getClass();

        model.setCommand(command);

        for (Method method : command.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExtraMainCommand.class)) {
                model.setMain(method);

                break;
            }
        }

        if (commandClass.isAnnotationPresent(ExtraHybridCommand.class)) {
            ExtraHybridCommand classAnnotation = commandClass.getAnnotation(ExtraHybridCommand.class);

            model.setName(classAnnotation.name().isEmpty() ? model.getMain().getName() : classAnnotation.name());
            model.setDescription(classAnnotation.description());

        // TODO: add logic
        } else
            throw new CommandAnnotationNotFoundException();

        return model;
    }
}
