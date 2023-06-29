package org.dwolf19.jdaextra.commands;

import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class Options {
    private final ArrayList<OptionData> options = new ArrayList<>();

    @NotNull
    public Options add(OptionData... optionData) {
        Collections.addAll(options, optionData);

        return this;
    }

    @NotNull
    public OptionData[] build() {
        return options.toArray(OptionData[]::new);
    }
}
