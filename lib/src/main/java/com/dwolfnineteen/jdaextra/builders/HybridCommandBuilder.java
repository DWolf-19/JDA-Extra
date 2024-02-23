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

import com.dwolfnineteen.jdaextra.annotations.ExtraHybridCommand;
import com.dwolfnineteen.jdaextra.annotations.commands.CommandLocalizationFunction;
import com.dwolfnineteen.jdaextra.annotations.commands.DescriptionLocalizations;
import com.dwolfnineteen.jdaextra.annotations.commands.Localization;
import com.dwolfnineteen.jdaextra.annotations.commands.NameLocalizations;
import com.dwolfnineteen.jdaextra.annotations.options.AutoComplete;
import com.dwolfnineteen.jdaextra.annotations.options.HybridOption;
import com.dwolfnineteen.jdaextra.annotations.options.Required;
import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import com.dwolfnineteen.jdaextra.commands.HybridCommand;
import com.dwolfnineteen.jdaextra.exceptions.CommandAnnotationNotFoundException;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.HybridCommandModel;
import com.dwolfnineteen.jdaextra.options.data.HybridOptionData;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.localization.ResourceBundleLocalizationFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hybrid command builder.
 */
public class HybridCommandBuilder extends CommandBuilder {
    /**
     * Construct new {@link HybridCommandBuilder}.
     *
     * @param command The hybrid command class.
     */
    public HybridCommandBuilder(@NotNull HybridCommand command) {
        this.command = command;
    }

    @Override
    @Nullable
    public HybridCommandModel buildModel() {
        HybridCommandModel model = new HybridCommandModel();
        Class<? extends BaseCommand> cls = command.getClass();

        model.setCommand(command);
        model.setMain(buildMain());

        ExtraHybridCommand annotation = cls.getAnnotation(ExtraHybridCommand.class);

        if (annotation == null) {
            throw new CommandAnnotationNotFoundException();
        }

        model.setName(annotation.name().isEmpty() ? model.getMain().getName() : annotation.name());
        model.setDescription(annotation.description());

        List<HybridOptionData> options = new ArrayList<>();

        for (Parameter parameter : model.getMain().getParameters()) {
            if (parameter.isAnnotationPresent(HybridOption.class)) {
                HybridOption hybridOption = parameter.getAnnotation(HybridOption.class);

                HybridOptionData data = new HybridOptionData(buildOptionType(parameter.getType(), hybridOption.type()),
                        hybridOption.name(),
                        hybridOption.description(),
                        parameter.isAnnotationPresent(Required.class),
                        parameter.isAnnotationPresent(AutoComplete.class));

                data.addChoices(buildChoices(parameter.getAnnotations()));

                options.add(data);
            }
        }

        model.setOptions(options);

        return buildSettings(model, cls);
    }

    /**
     * @param model The command model.
     * @param cls The command class.
     * @return
     */
    @Override
    @NotNull
    protected HybridCommandModel buildSettings(@NotNull CommandModel model, @NotNull Class<? extends BaseCommand> cls) {
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

        return ((HybridCommandModel) super.buildSettings(model, cls))
                .setNameLocalizations(nameLocalizations)
                .setDescriptionLocalizations(descriptionLocalizations)
                .setLocalizationFunction(localizationFunctionAnnotation == null
                        ? ResourceBundleLocalizationFunction.empty().build()
                        : ResourceBundleLocalizationFunction.fromBundles(localizationFunctionAnnotation.baseName(),
                        localizationFunctionAnnotation.locales()).build());
    }
}
