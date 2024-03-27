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

import com.dwolfnineteen.jdaextra.annotations.ExtraSlashCommand;
import com.dwolfnineteen.jdaextra.annotations.options.AutoComplete;
import com.dwolfnineteen.jdaextra.annotations.options.Required;
import com.dwolfnineteen.jdaextra.annotations.options.SlashOption;
import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import com.dwolfnineteen.jdaextra.commands.SlashCommand;
import com.dwolfnineteen.jdaextra.exceptions.CommandAnnotationNotFoundException;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.SlashCommandModel;
import com.dwolfnineteen.jdaextra.options.data.CommandOptionData;
import com.dwolfnineteen.jdaextra.options.data.SlashOptionData;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Slash command builder.
 */
public class SlashCommandBuilder extends SlashLikeCommandBuilder {
    /**
     * Construct new {@link SlashCommandBuilder}.
     *
     * @param command The slash command class.
     */
    public SlashCommandBuilder(@NotNull SlashCommand command) {
        this.command = command;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public @Nullable SlashCommandModel buildModel() {
        SlashCommandModel model = new SlashCommandModel();
        Class<? extends BaseCommand> cls = command.getClass();
        ExtraSlashCommand annotation = cls.getAnnotation(ExtraSlashCommand.class);

        if (annotation == null) {
            throw new CommandAnnotationNotFoundException();
        }

        model.setCommand(command)
                .setMain(buildMain())
                .setName(annotation.name().isEmpty() ? model.getMain().getName() : annotation.name())
                .setDescription(annotation.description())
                .setOptions(buildOptions(model));

        return buildSettings(model, cls);
    }

    /**
     * {@inheritDoc}
     *
     * @param model {@inheritDoc}
     * @param <T> {@inheritDoc}
     * @return {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T extends CommandOptionData> @NotNull List<T> buildOptions(@NotNull CommandModel model) {
        List<T> options = new ArrayList<>();

        for (Parameter parameter : model.getMain().getParameters()) {
            if (parameter.isAnnotationPresent(SlashOption.class)) {
                SlashOption slashOption = parameter.getAnnotation(SlashOption.class);

                T data = (T) new SlashOptionData(buildOptionType(parameter.getType(), slashOption.type()),
                        slashOption.name(),
                        slashOption.description(),
                        parameter.isAnnotationPresent(Required.class),
                        parameter.isAnnotationPresent(AutoComplete.class));

                data.addChoices(buildChoices(parameter.getAnnotations()));

                options.add(data);
            }
        }

        return options;
    }

    /**
     * {@inheritDoc}
     *
     * @param parameterType {@inheritDoc}
     * @param typeFromAnnotation {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    protected @NotNull OptionType buildOptionType(@NotNull Class<?> parameterType,
                                                  @NotNull OptionType typeFromAnnotation) {
        return typeFromAnnotation == OptionType.UNKNOWN && parameterType.equals(Message.Attachment.class)
                ? OptionType.ATTACHMENT
                : super.buildOptionType(parameterType, typeFromAnnotation);
    }

    /**
     * {@inheritDoc}
     *
     * @param model {@inheritDoc}
     * @param cls {@inheritDoc}
     * @return Configured {@link SlashCommandModel}.
     */
    @Override
    protected @NotNull SlashCommandModel buildSettings(@NotNull CommandModel model,
                                                       @NotNull Class<? extends BaseCommand> cls) {
        return (SlashCommandModel) super.buildSettings(model, cls);
    }
}
