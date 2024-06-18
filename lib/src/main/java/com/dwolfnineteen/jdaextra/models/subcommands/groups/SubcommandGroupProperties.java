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
package com.dwolfnineteen.jdaextra.models.subcommands.groups;

// TODO: Add field for subcommands
// TODO: Removing subcommands

import com.dwolfnineteen.jdaextra.commands.subcommandgroups.BaseSubcommandGroup;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public abstract class SubcommandGroupProperties {
    /**
     *
     */
    protected BaseSubcommandGroup groupClass;

    /**
     * @return
     */
    public @NotNull BaseSubcommandGroup getGroupClass() {
        return groupClass;
    }

    /**
     * @return
     */
    public abstract String getName();

    /**
     * @return
     */
    public abstract String getDescription();

    /**
     * @param groupClass
     * @return
     */
    public abstract SubcommandGroupProperties setGroupClass(BaseSubcommandGroup groupClass);

    /**
     * @param name
     * @return
     */
    public abstract SubcommandGroupProperties setName(String name);

    /**
     * @param description
     * @return
     */
    public abstract SubcommandGroupProperties setDescription(String description);
}
