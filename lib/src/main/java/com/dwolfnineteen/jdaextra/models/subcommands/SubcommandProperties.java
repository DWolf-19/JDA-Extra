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

import com.dwolfnineteen.jdaextra.options.data.CommandOptionData;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

// TODO: Cool idea, review it https://discord.com/channels/125227483518861312/1251268109730320546/1251271685265690664
/**
 *
 */
public abstract class SubcommandProperties {
    /**
     *
     */
    protected Method entryPoint;

    /**
     * @return
     */
    public abstract Method getEntryPoint();

    /**
     * @return
     */
    public abstract String getName();

    /**
     * @return
     */
    public abstract String getDescription();

    /**
     * @return
     */
    public abstract List<? extends CommandOptionData> getOptions();

    /**
     * @param entryPoint
     * @return
     */
    public abstract SubcommandProperties setEntryPoint(Method entryPoint);

    /**
     * @param name
     * @return
     */
    public abstract SubcommandProperties setName(String name);

    /**
     * @param description
     * @return
     */
    public abstract SubcommandProperties setDescription(String description);

    /**
     * @param type
     * @param name
     * @param description
     * @return
     */
    public abstract SubcommandProperties addOption(OptionType type, String name, String description);

    /**
     * @param type
     * @param name
     * @param description
     * @param required
     * @return
     */
    public abstract SubcommandProperties addOption(OptionType type, String name, String description, boolean required);

    /**
     * @param type
     * @param name
     * @param description
     * @param required
     * @param autocomplete
     * @return
     */
    public abstract SubcommandProperties addOption(OptionType type, String name, String description, boolean required, boolean autocomplete);

    /**
     * @param options
     * @return
     */
    public abstract SubcommandProperties addOptions(Collection<CommandOptionData> options);

    /**
     * @param options
     * @return
     */
    public abstract SubcommandProperties addOptions(CommandOptionData... options);

    // TODO: Methods to removing options
}
