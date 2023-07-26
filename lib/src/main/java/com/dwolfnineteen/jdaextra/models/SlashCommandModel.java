package com.dwolfnineteen.jdaextra.models;

import org.jetbrains.annotations.NotNull;

public class SlashCommandModel extends CommandModel {
    private String description;

    @NotNull
    public String getDescription() {
        return description;
    }

    @NotNull
    public SlashCommandModel setDescription(@NotNull String description) {
        this.description = description;

        return this;
    }
}
