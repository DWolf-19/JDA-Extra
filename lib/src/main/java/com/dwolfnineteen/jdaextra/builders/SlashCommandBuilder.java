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
import com.dwolfnineteen.jdaextra.annotations.commands.CommandLocalizationFunction;
import com.dwolfnineteen.jdaextra.annotations.commands.DescriptionLocalizations;
import com.dwolfnineteen.jdaextra.annotations.commands.Localization;
import com.dwolfnineteen.jdaextra.annotations.commands.NameLocalizations;
import com.dwolfnineteen.jdaextra.annotations.options.AutoComplete;
import com.dwolfnineteen.jdaextra.annotations.options.Required;
import com.dwolfnineteen.jdaextra.annotations.options.SlashOption;
import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import com.dwolfnineteen.jdaextra.commands.SlashCommand;
import com.dwolfnineteen.jdaextra.exceptions.CommandAnnotationNotFoundException;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.SlashCommandModel;
import com.dwolfnineteen.jdaextra.options.data.SlashOptionData;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.localization.ResourceBundleLocalizationFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Slash command builder.
 */
public class SlashCommandBuilder extends CommandBuilder {
    /**
     * Construct new {@link SlashCommandBuilder}.
     *
     * @param command The slash command class.
     */
    public SlashCommandBuilder(@NotNull SlashCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public SlashCommandModel buildModel() {
        SlashCommandModel model = new SlashCommandModel();
        Class<? extends BaseCommand> cls = command.getClass();

        model.setCommand(command);
        model.setMain(buildMain());

        ExtraSlashCommand annotation = cls.getAnnotation(ExtraSlashCommand.class);

        if (annotation == null) {
            throw new CommandAnnotationNotFoundException();
        }

        model.setName(annotation.name().isEmpty() ? model.getMain().getName() : annotation.name());
        model.setDescription(annotation.description());

        List<SlashOptionData> options = new ArrayList<>();

        for (Parameter parameter : model.getMain().getParameters()) {
            if (parameter.isAnnotationPresent(SlashOption.class)) {
                SlashOption slashOption = parameter.getAnnotation(SlashOption.class);

                SlashOptionData data = new SlashOptionData(buildOptionType(parameter.getType(), slashOption.type()),
                        slashOption.name(),
                        slashOption.description(),
                        parameter.isAnnotationPresent(Required.class),
                        parameter.isAnnotationPresent(AutoComplete.class));

                data.addChoices(buildChoices(parameter.getAnnotations()));

                options.add(data);
            }
        }

        model.setOptions(options);

        return buildSettings(model, cls);
    }

    @Override
    @NotNull
    protected OptionType buildOptionType(@NotNull Class<?> parameterType, @NotNull OptionType typeFromAnnotation) {
        return typeFromAnnotation == OptionType.UNKNOWN && parameterType.equals(Message.Attachment.class)
                ? OptionType.ATTACHMENT
                : super.buildOptionType(parameterType, typeFromAnnotation);
    }

    /**
     * @param model The command model. 
     * @param cls The command class.
     * @return
     */
    @Override
    @NotNull
    protected SlashCommandModel buildSettings(@NotNull CommandModel model, @NotNull Class<? extends BaseCommand> cls) {
        Map<DiscordLocale, String> nameLocalizations = new HashMap<>();
        Map<DiscordLocale, String> descriptionLocalizations = new HashMap<>();

        NameLocalizations nameLocalizationsAnnotation = cls.getAnnotation(NameLocalizations.class);
        DescriptionLocalizations descriptionLocalizationsAnnotation = cls.getAnnotation(DescriptionLocalizations.class);
        CommandLocalizationFunction localizationFunctionAnnotation = cls.getAnnotation(CommandLocalizationFunction.class);

        if (nameLocalizationsAnnotation != null) {
            for (Localization localization : nameLocalizationsAnnotation.value()) {
                nameLocalizations.put(localization.locale(), localization.string());
            }
        }

        if (descriptionLocalizationsAnnotation != null) {
            for (Localization localization : descriptionLocalizationsAnnotation.value()) {
                descriptionLocalizations.put(localization.locale(), localization.string());
            }
        }

        return ((SlashCommandModel) super.buildSettings(model, cls))
                .setNameLocalizations(nameLocalizations)
                .setDescriptionLocalizations(descriptionLocalizations)
                .setLocalizationFunction(localizationFunctionAnnotation == null
                        ? ResourceBundleLocalizationFunction.empty().build()
                        : ResourceBundleLocalizationFunction.fromBundles(localizationFunctionAnnotation.baseName(),
                        localizationFunctionAnnotation.locales()).build());
    }
}
