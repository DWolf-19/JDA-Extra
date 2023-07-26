package com.dwolfnineteen.jdaextra.models;

import org.jetbrains.annotations.NotNull;

public class HybridCommandModel extends CommandModel {
    private String description;

    @NotNull
    public String getDescription() {
        return description;
    }

    @NotNull
    public HybridCommandModel setDescription(@NotNull String description) {
        this.description = description;

        return this;
    }
}
