package org.dwolf19.jdaextra;

public abstract class Interaction {

    protected boolean guildOnly = true;
    protected int cooldown = 0;  // Seconds

    protected boolean isGuildOnly() {
        return guildOnly;
    }

    protected int getCooldown() {
        return cooldown;
    }

}
