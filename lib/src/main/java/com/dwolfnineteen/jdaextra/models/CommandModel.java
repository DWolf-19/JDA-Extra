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
package com.dwolfnineteen.jdaextra.models;

import com.dwolfnineteen.jdaextra.commands.BaseCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public abstract class CommandModel {
    private BaseCommand command;
    private Method main;
    private String name;
    private boolean guildOnly;
    private boolean nsfw;

    @NotNull
    public BaseCommand getCommand() {
        return command;
    }

    @Nullable
    public Method getMain() {
        return main;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public boolean isGuildOnly() {
        return guildOnly;
    }

    public boolean isNSFW() {
        return nsfw;
    }

    @NotNull
    public CommandModel setCommand(@NotNull BaseCommand command) {
        this.command = command;

        return this;
    }

    @NotNull
    public CommandModel setMain(@NotNull Method main) {
        this.main = main;

        return this;
    }

    @NotNull
    public CommandModel setName(@NotNull String name) {
        this.name = name;

        return this;
    }

    @NotNull
    public CommandModel setGuildOnly(boolean guildOnly) {
        this.guildOnly = guildOnly;

        return this;
    }

    @NotNull
    public CommandModel setNSFW(boolean nsfw) {
        this.nsfw = nsfw;

        return this;
    }
}
