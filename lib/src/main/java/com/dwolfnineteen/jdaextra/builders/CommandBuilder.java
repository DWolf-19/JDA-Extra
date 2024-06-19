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

import com.dwolfnineteen.jdaextra.annotations.ExtraMainCommand;
import com.dwolfnineteen.jdaextra.annotations.commands.DescriptionLocalizations;
import com.dwolfnineteen.jdaextra.annotations.commands.GuildOnly;
import com.dwolfnineteen.jdaextra.annotations.commands.Localization;
import com.dwolfnineteen.jdaextra.annotations.commands.NameLocalizations;
import com.dwolfnineteen.jdaextra.annotations.options.ChoiceDouble;
import com.dwolfnineteen.jdaextra.annotations.options.ChoiceLong;
import com.dwolfnineteen.jdaextra.annotations.options.ChoiceString;
import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import com.dwolfnineteen.jdaextra.models.CommandModel;
import com.dwolfnineteen.jdaextra.models.subcommands.SubcommandProperties;
import com.dwolfnineteen.jdaextra.models.subcommands.groups.SubcommandGroupProperties;
import com.dwolfnineteen.jdaextra.options.data.CommandOptionData;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Base command builder.
 */
public abstract class CommandBuilder {
    /**
     * The command class.
     */
    protected BaseCommand command;

    /**
     * Build the command model.
     *
     * @return The command model.
     */
    public abstract CommandModel buildModel();

    // All methods below are necessary just to keep from going crazy and not overweight buildModel()
    // SOLID <3

    /**
     * Build the command <strong>main</strong> entry point.
     *
     * @return The entry point.
     */
    protected @Nullable Method buildMain() {
        for (Method method : command.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExtraMainCommand.class)) {
                return method;
            }
        }

        return null;
    }

    /**
     * Build the command options.
     *
     * @param commandMethod The command method in the command class.
     * @return {@link List} of {@link CommandOptionData}.
     */
    protected abstract List<? extends CommandOptionData> buildOptions(Method commandMethod);

    /**
     * Build option type.
     *
     * @param parameterType Parameter type from Java Reflection API.
     * @param typeFromAnnotation {@link OptionType} defined by annotation (default: {@link OptionType#UNKNOWN UNKNOWN}).
     * If {@link OptionType#UNKNOWN UNKNOWN}, parameter type will be used.
     * @return The {@link OptionType}.
     * @see com.dwolfnineteen.jdaextra.annotations.options.HybridOption HybridOption
     */
    protected @NotNull OptionType buildOptionType(@NotNull Class<?> parameterType,
                                                  @NotNull OptionType typeFromAnnotation) {
        OptionType type;

        if (typeFromAnnotation == OptionType.UNKNOWN) {
            if (parameterType.equals(Boolean.class)) {
                type = OptionType.BOOLEAN;
            } else if (parameterType.equals(Channel.class)) {
                type = OptionType.CHANNEL;
            } else if (parameterType.equals(Long.class)) {
                type = OptionType.INTEGER;
            } else if (parameterType.equals(IMentionable.class)) {
                type = OptionType.MENTIONABLE;
            } else if (parameterType.equals(Double.class)) {
                type = OptionType.NUMBER;
            } else if (parameterType.equals(String.class)) {
                type = OptionType.STRING;
            } else if (parameterType.equals(Member.class) || parameterType.equals(User.class)) {
                type = OptionType.USER;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            type = typeFromAnnotation;
        }

        return type;
    }

    // TODO: Rename to buildOptionChoices
    /**
     * Build command choices from option annotations.
     *
     * @param annotations Option annotations.
     * @return {@link List} of {@link Command.Choice}.
     */
    protected @NotNull List<Command.Choice> buildChoices(@NotNull Annotation[] annotations) {
        List<Command.Choice> choices = new ArrayList<>();

        for (Annotation annotation : annotations) {
            if (annotation instanceof ChoiceDouble) {
                choices.add(new Command.Choice(((ChoiceDouble) annotation).name(),
                        ((ChoiceDouble) annotation).val()));
            } else if (annotation instanceof ChoiceLong) {
                choices.add(new Command.Choice(((ChoiceLong) annotation).name(),
                        ((ChoiceLong) annotation).val()));
            } else if (annotation instanceof ChoiceString) {
                choices.add(new Command.Choice(((ChoiceString) annotation).name(),
                        ((ChoiceString) annotation).val()));
            }
        }

        return choices;
    }

    /**
     * @return
     */
    protected abstract List<? extends SubcommandProperties> buildSubcommands();

    /**
     * @return
     */
    protected abstract List<? extends SubcommandGroupProperties> buildSubcommandGroups();

    // TODO: More settings
    /**
     * Build command settings (such as {@link GuildOnly @GuildOnly}).
     *
     * @param model The command model.
     * @param clazz The command class.
     * @return Configured {@link CommandModel}.
     */
    protected @NotNull CommandModel buildSettings(@NotNull CommandModel model,
                                                  @NotNull Class<? extends BaseCommand> clazz) {
        model.setGuildOnly(clazz.isAnnotationPresent(GuildOnly.class));

        return model;
    }

    /**
     * @param entryPoint
     * @return
     */
    protected @NotNull Map<DiscordLocale, String> buildNameLocalizations(@NotNull Method entryPoint) {
        Map<DiscordLocale, String> localizations = new HashMap<>();
        NameLocalizations localizationsAnnotation = entryPoint.getAnnotation(NameLocalizations.class);

        if (localizationsAnnotation != null) {
            localizations.putAll(convertLocalizationsToMap(localizationsAnnotation.value()));
        }

        return localizations;
    }

    /**
     * @param clazz
     * @return
     */
    protected @NotNull Map<DiscordLocale, String> buildNameLocalizations(@NotNull Class<?> clazz) {
        Map<DiscordLocale, String> localizations = new HashMap<>();
        NameLocalizations localizationsAnnotation = clazz.getAnnotation(NameLocalizations.class);

        if (localizationsAnnotation != null) {
            localizations.putAll(convertLocalizationsToMap(localizationsAnnotation.value()));
        }

        return localizations;
    }

    /**
     * @param entryPoint
     * @return
     */
    protected @NotNull Map<DiscordLocale, String> buildDescriptionLocalizations(@NotNull Method entryPoint) {
        Map<DiscordLocale, String> localizations = new HashMap<>();
        DescriptionLocalizations localizationsAnnotation = entryPoint.getAnnotation(DescriptionLocalizations.class);

        if (localizationsAnnotation != null) {
            localizations.putAll(convertLocalizationsToMap(localizationsAnnotation.value()));
        }

        return localizations;
    }

    /**
     * @param clazz
     * @return
     */
    protected @NotNull Map<DiscordLocale, String> buildDescriptionLocalizations(@NotNull Class<?> clazz) {
        Map<DiscordLocale, String> localizations = new HashMap<>();
        DescriptionLocalizations localizationsAnnotation = clazz.getAnnotation(DescriptionLocalizations.class);

        if (localizationsAnnotation != null) {
            localizations.putAll(convertLocalizationsToMap(localizationsAnnotation.value()));
        }

        return localizations;
    }

    /**
     * @param localizations
     * @return
     */
    protected @NotNull Map<DiscordLocale, String> convertLocalizationsToMap(Localization[] localizations) {
        return Arrays.stream(localizations).collect(Collectors.toMap(Localization::locale, Localization::string));
    }
}
