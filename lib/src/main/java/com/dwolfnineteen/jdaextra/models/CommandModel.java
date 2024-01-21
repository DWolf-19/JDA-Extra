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

/**
 * Command models is a {@link com.dwolfnineteen.jdaextra.builders builders} output
 * and created for better command classes representation.
 */
public abstract class CommandModel {
    // TODO: private List<CommandOptionData> options
    private BaseCommand command;
    private Method main;
    private String name;
    private boolean guildOnly;
    private boolean nsfw;

    /**
     * The command class inherited from one of the {@link com.dwolfnineteen.jdaextra.commands commands}.
     *
     * @return The command class.
     */
    @NotNull
    public BaseCommand getCommand() {
        return command;
    }

    /**
     * The command <strong>main</strong> entry point.
     * <br>
     * Nullability will improve quality of code in the case where command doesn't have a main entry point,
     * but have subcommands/subcommand groups:
     * <pre>
     * {@code // This is an empty method and does nothing.
     * // You may see this in old bots on the JDA-Utilities framework.
     * // With JDA-Extra you can avoid this. Just don't write it!
     * @ExtraMainCommand
     * public void myCommand(CommandEvent event) {
     * }
     *
     * @ExtraSubCommand
     * public void mySubCommand(SubCommandEvent event) {
     *      event.reply("This is incredible!").queue();
     * }
     * }
     * </pre>
     *
     * @return The entry point. {@code null} if the command doesn't have a main entry point.
     * @see com.dwolfnineteen.jdaextra.annotations.ExtraMainCommand ExtraMainCommand
     */
    @Nullable
    public Method getMain() {
        return main;
    }

    /**
     * The command name.
     *
     * @return The name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Whether this command can be executed only on guilds.
     *
     * @return Whether this command is guild only.
     */
    public boolean isGuildOnly() {
        return guildOnly;
    }

    /**
     * Whether this command can be executed only in NSFW channels.
     *
     * @return Whether this command is NSFW.
     */
    public boolean isNSFW() {
        return nsfw;
    }

    /**
     * Sets the command class inherited from one of the {@link com.dwolfnineteen.jdaextra.commands commands}.
     *
     * @param command The command class.
     * @return The {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} instance, for chaining.
     */
    @NotNull
    public CommandModel setCommand(@NotNull BaseCommand command) {
        this.command = command;

        return this;
    }

    /**
     * Sets the command <strong>main</strong> entry point.
     *
     * @param main The command entry point.
     * @return The {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} instance, for chaining.
     * @see #getMain() getMain()
     */
    @NotNull
    public CommandModel setMain(@NotNull Method main) {
        this.main = main;

        return this;
    }

    /**
     * Sets the command name.
     *
     * @param name The name.
     * @return The {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} instance, for chaining.
     */
    @NotNull
    public CommandModel setName(@NotNull String name) {
        this.name = name;

        return this;
    }

    /**
     * Whether this command can be executed only on guilds.
     *
     * @param guildOnly Whether this command is guild only.
     * @return The {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} instance, for chaining.
     */
    @NotNull
    public CommandModel setGuildOnly(boolean guildOnly) {
        this.guildOnly = guildOnly;

        return this;
    }

    /**
     * Whether this command can be executed only in NSFW channels.
     *
     * @param nsfw Whether this command is NSFW only.
     * @return The {@link com.dwolfnineteen.jdaextra.models.CommandModel CommandModel} instance, for chaining.
     */
    @NotNull
    public CommandModel setNSFW(boolean nsfw) {
        this.nsfw = nsfw;

        return this;
    }
}
