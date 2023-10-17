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
package com.dwolfnineteen.jdaextra.options.mappings;

import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Add OptionType checks to getters
public class PrefixOptionMapping implements CommandOptionMapping {
    private final OptionType type;
    private final String name;
    private final String option;
    private final MessageReceivedEvent event;

    public PrefixOptionMapping(@NotNull OptionType type,
                               @NotNull String name,
                               @NotNull String option,
                               @NotNull MessageReceivedEvent event) {
        this.type = type;
        this.name = name;
        this.option = option;
        this.event = event;
    }

    @Override
    @NotNull
    public OptionType getType() {
        return type;
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    @NotNull
    public String getAsString() {
        return option;
    }

    @Override
    public boolean getAsBoolean() {
        return Boolean.parseBoolean(option);
    }

    @Override
    public long getAsLong() {
        return Long.parseLong(option);
    }

    @Override
    public int getAsInt() {
        return Integer.parseInt(option);
    }

    @Override
    public double getAsDouble() {
        return Double.parseDouble(option);
    }

    @Override
    @Nullable
    public IMentionable getAsMentionable() {
        if (getAsMember() != null) {
            return getAsMember();
        } else if (getAsUser() != null) {
            return getAsUser();
        } else if (getAsRole() != null) {
            return getAsRole();
        }

        return null;
    }

    @Override
    @Nullable
    public Member getAsMember() {
        return event.getGuild().getMemberById(option);
    }

    @Override
    @Nullable
    public User getAsUser() {
        return event.getJDA().getUserById(option);
    }

    @Override
    @Nullable
    public Role getAsRole() {
        return event.getGuild().getRoleById(option);
    }

    @Override
    @Nullable
    public GuildChannelUnion getAsChannel() {
        return event.getGuild().getChannelById(GuildChannelUnion.class, option);
    }

    @Override
    @Nullable
    public ChannelType getChannelType() {
        return getAsChannel().getType();
    }
}
