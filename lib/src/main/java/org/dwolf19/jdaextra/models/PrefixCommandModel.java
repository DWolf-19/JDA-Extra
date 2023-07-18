package org.dwolf19.jdaextra.models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrefixCommandModel extends CommandModel {
    private String description;

    @Nullable
    public String getDescription() {
        return description;
    }

    @NotNull
    public PrefixCommandModel setDescription(@Nullable String description) {
        this.description = description;

        return this;
    }
}
