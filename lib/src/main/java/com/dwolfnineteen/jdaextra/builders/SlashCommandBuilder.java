package com.dwolfnineteen.jdaextra.builders;

import com.dwolfnineteen.jdaextra.annotations.ExtraMainCommand;
import com.dwolfnineteen.jdaextra.annotations.ExtraSlashCommand;
import com.dwolfnineteen.jdaextra.commands.Command;
import com.dwolfnineteen.jdaextra.commands.SlashCommand;
import com.dwolfnineteen.jdaextra.exceptions.CommandAnnotationNotFoundException;
import com.dwolfnineteen.jdaextra.models.SlashCommandModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public class SlashCommandBuilder extends CommandBuilder {
    public SlashCommandBuilder(@NotNull SlashCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public SlashCommandModel buildModel() {
        SlashCommandModel model = new SlashCommandModel();
        Class<? extends Command> commandClass = command.getClass();

        model.setCommand(command);

        for (Method method : command.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExtraMainCommand.class)) {
                model.setMain(method);

                break;
            }
        }

        if (commandClass.isAnnotationPresent(ExtraSlashCommand.class)) {
            ExtraSlashCommand classAnnotation = commandClass.getAnnotation(ExtraSlashCommand.class);

            model.setName(classAnnotation.name().isEmpty() ? model.getMain().getName() : classAnnotation.name());
            model.setDescription(classAnnotation.description());

        // TODO: add logic
        } else
            throw new CommandAnnotationNotFoundException();

        return model;
    }
}
