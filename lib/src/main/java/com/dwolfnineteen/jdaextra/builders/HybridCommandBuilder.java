package com.dwolfnineteen.jdaextra.builders;

import com.dwolfnineteen.jdaextra.annotations.ExtraHybridCommand;
import com.dwolfnineteen.jdaextra.annotations.ExtraMainCommand;
import com.dwolfnineteen.jdaextra.commands.HybridCommand;
import com.dwolfnineteen.jdaextra.commands.Command;
import com.dwolfnineteen.jdaextra.exceptions.CommandAnnotationNotFoundException;
import com.dwolfnineteen.jdaextra.models.HybridCommandModel;
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
