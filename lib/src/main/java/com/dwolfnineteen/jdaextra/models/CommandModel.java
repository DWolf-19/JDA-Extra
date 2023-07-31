/*
Copyright (c) 2023 DWolf Nineteen & The JDA-Extra contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.dwolfnineteen.jdaextra.models;

import com.dwolfnineteen.jdaextra.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class CommandModel {
    private Command command;
    private Method main;
    private String name;
    private ArrayList<OptionData> options;
    private boolean guildOnly;
    private boolean nsfw;

    @NotNull
    public Command getCommand() {
        return command;
    }

    @NotNull
    public Method getMain() {
        return main;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public ArrayList<OptionData> getOptions() {
        return options;
    }

    public boolean isGuildOnly() {
        return guildOnly;
    }

    public boolean isNSFW() {
        return nsfw;
    }

    @NotNull
    public CommandModel setCommand(@NotNull Command command) {
        this.command = command;

        return this;
    }

    @NotNull
    public CommandModel setMain(@NotNull Method main) {
        this.main = main;

        return this;
    }

    public CommandModel setName(@NotNull String name) {
        this.name = name;

        return this;
    }

    public CommandModel setOptions(@NotNull ArrayList<OptionData> options) {
        this.options = options;

        return this;
    }

    public CommandModel setGuildOnly(boolean guildOnly) {
        this.guildOnly = guildOnly;

        return this;
    }

    public CommandModel setNSFW(boolean nsfw) {
        this.nsfw = nsfw;

        return this;
    }
}
