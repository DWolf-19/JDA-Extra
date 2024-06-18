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
package com.dwolfnineteen.jdaextra.models.subcommands;

import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.localization.LocalizationMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 *
 */
public abstract class SlashLikeSubcommandProperties extends SubcommandProperties implements GeneralSubcommandData {
    /**
     *
     */
    protected SubcommandData generalData;

    /**
     * @param name
     * @param description
     */
    public SlashLikeSubcommandProperties(@NotNull String name, @NotNull String description) {
        generalData = new SubcommandData(name, description);
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return generalData.getName();
    }

    /**
     * @return
     */
    @Override
    public @NotNull String getDescription() {
        return generalData.getDescription();
    }

    /**
     * @return
     */
    public @NotNull LocalizationMap getNameLocalizations() {
        return generalData.getNameLocalizations();
    }

    /**
     * @return
     */
    public @NotNull LocalizationMap getDescriptionLocalizations() {
        return generalData.getDescriptionLocalizations();
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    public abstract SlashLikeSubcommandProperties setNameLocalization(DiscordLocale locale, String name);

    /**
     * @param localizations
     * @return
     */
    public abstract SlashLikeSubcommandProperties setNameLocalizations(Map<DiscordLocale, String> localizations);

    /**
     * @param locale
     * @param name
     * @return
     */
    public abstract SlashLikeSubcommandProperties setDescriptionLocalization(DiscordLocale locale, String name);

    /**
     * @param localizations
     * @return
     */
    public abstract SlashLikeSubcommandProperties setDescriptionLocalizations(Map<DiscordLocale, String> localizations);
}
