package org.dwolf19.jdaextra.entities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PrefixCommandEntity {
    private String prefix;
    private String name;
    private ArrayList<String> args;

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public ArrayList<String> getArgs() {
        return args;
    }

    public void setPrefix(@NotNull String prefix) {
        this.prefix = prefix;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setArgs(@NotNull ArrayList<String> args) {
        this.args = args;
    }
}
