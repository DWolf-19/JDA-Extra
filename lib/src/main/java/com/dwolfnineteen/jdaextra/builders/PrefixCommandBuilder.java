/*
 * Copyright (c) 2023 DWolf Nineteen & The JDA-Extra contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.dwolfnineteen.jdaextra.builders;

import com.dwolfnineteen.jdaextra.annotations.ExtraPrefixCommand;
import com.dwolfnineteen.jdaextra.annotations.options.AutoComplete;
import com.dwolfnineteen.jdaextra.annotations.options.PrefixOption;
import com.dwolfnineteen.jdaextra.annotations.options.Required;
import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import com.dwolfnineteen.jdaextra.commands.PrefixCommand;
import com.dwolfnineteen.jdaextra.exceptions.CommandAnnotationNotFoundException;
import com.dwolfnineteen.jdaextra.models.PrefixCommandModel;
import com.dwolfnineteen.jdaextra.options.data.PrefixOptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Prefix command builder.
 */
public class PrefixCommandBuilder extends CommandBuilder {
    /**
     * Construct new {@link PrefixCommandBuilder}.
     *
     * @param command The prefix command class.
     */
    public PrefixCommandBuilder(@NotNull PrefixCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public PrefixCommandModel buildModel() {
        PrefixCommandModel model = new PrefixCommandModel();
        Class<? extends BaseCommand> cls = command.getClass();

        model.setCommand(command);
        model.setMain(buildMain());

        ExtraPrefixCommand annotation = cls.getAnnotation(ExtraPrefixCommand.class);

        if (annotation == null) {
            throw new CommandAnnotationNotFoundException();
        }

        model.setName(annotation.name().isEmpty() ? model.getMain().getName() : annotation.name());
        model.setDescription(annotation.description().isEmpty() ? null : annotation.description());

        List<PrefixOptionData> options = new ArrayList<>();

        for (Parameter parameter : model.getMain().getParameters()) {
            if (parameter.isAnnotationPresent(PrefixOption.class)) {
                PrefixOption prefixOption = parameter.getAnnotation(PrefixOption.class);

                PrefixOptionData data = new PrefixOptionData(buildOptionType(parameter.getType(), prefixOption.type()),
                        prefixOption.name(),
                        prefixOption.description().isEmpty() ? null : prefixOption.description(),
                        parameter.isAnnotationPresent(Required.class),
                        parameter.isAnnotationPresent(AutoComplete.class));

                options.add(data);
            }
        }

        model.setOptions(options);

        return (PrefixCommandModel) buildSettings(model, cls);
    }
}
