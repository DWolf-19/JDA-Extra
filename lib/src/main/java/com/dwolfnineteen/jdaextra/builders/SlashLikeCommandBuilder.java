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

import com.dwolfnineteen.jdaextra.annotations.commands.CommandLocalizationFunction;
import com.dwolfnineteen.jdaextra.annotations.commands.DescriptionLocalizations;
import com.dwolfnineteen.jdaextra.annotations.commands.Localization;
import com.dwolfnineteen.jdaextra.annotations.commands.NameLocalizations;
import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.SlashLikeCommandModel;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.localization.ResourceBundleLocalizationFunction;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Base builder for commands that can be executed as a slash (regular slash/hybrid).
 */
public abstract class SlashLikeCommandBuilder extends CommandBuilder {
    /**
     * Build command settings (such as {@link com.dwolfnineteen.jdaextra.annotations.commands.GuildOnly @GuildOnly}).
     *
     * @param model The command model.
     * @param cls The command class.
     * @return Configured {@link SlashLikeCommandModel}.
     */
    @Override
    @NotNull
    protected SlashLikeCommandModel buildSettings(@NotNull CommandModel model, @NotNull Class<? extends BaseCommand> cls) {
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

        return ((SlashLikeCommandModel) super.buildSettings(model, cls))
                .setNameLocalizations(nameLocalizations)
                .setDescriptionLocalizations(descriptionLocalizations)
                .setLocalizationFunction(localizationFunctionAnnotation == null
                        ? ResourceBundleLocalizationFunction.empty().build()
                        : ResourceBundleLocalizationFunction.fromBundles(localizationFunctionAnnotation.baseName(),
                        localizationFunctionAnnotation.locales()).build());
    }
}
