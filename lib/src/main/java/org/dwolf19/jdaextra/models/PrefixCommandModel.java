package org.dwolf19.jdaextra.models;

import org.jetbrains.annotations.NotNull;

public class PrefixCommandModel extends CommandModel {
    private String prefix;

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    public PrefixCommandModel setPrefix(@NotNull String prefix) {
        this.prefix = prefix;

        return this;
    }
}
