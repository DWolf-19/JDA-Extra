/*
Copyright (c) 2023 DWolf Nineteen & The JDA-Extra contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.dwolfnineteen.jdaextra.builders;

import com.dwolfnineteen.jdaextra.annotations.ExtraHybridCommand;
import com.dwolfnineteen.jdaextra.commands.HybridCommand;
import com.dwolfnineteen.jdaextra.commands.Command;
import com.dwolfnineteen.jdaextra.exceptions.CommandAnnotationNotFoundException;
import com.dwolfnineteen.jdaextra.models.HybridCommandModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        model.setMain(buildMain());

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
