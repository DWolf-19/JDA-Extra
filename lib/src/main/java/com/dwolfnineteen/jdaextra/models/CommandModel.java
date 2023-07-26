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
