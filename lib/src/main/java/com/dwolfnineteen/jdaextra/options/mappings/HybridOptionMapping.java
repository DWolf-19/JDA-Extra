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
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;

/**
 * Name/value mapping of {@link com.dwolfnineteen.jdaextra.events.HybridCommandEvent HybridCommandEvent} option.
 */
public class HybridOptionMapping implements CommandOptionMapping {
    private final CommandOptionMapping mapping;

    /**
     * Construct new {@link com.dwolfnineteen.jdaextra.options.mappings.HybridOptionMapping HybridOptionMapping}.
     *
     * @param mapping The {@link com.dwolfnineteen.jdaextra.options.mappings.CommandOptionMapping CommandOptionMapping}
     * for "containerization".
     */
    public HybridOptionMapping(@NotNull CommandOptionMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public OptionType getType() {
        return mapping.getType();
    }

    @Override
    public String getName() {
        return mapping.getName();
    }

    @Override
    public String getAsString() {
        return mapping.getAsString();
    }

    @Override
    public boolean getAsBoolean() {
        return mapping.getAsBoolean();
    }

    @Override
    public long getAsLong() {
        return mapping.getAsLong();
    }

    @Override
    public int getAsInt() {
        return mapping.getAsInt();
    }

    @Override
    public double getAsDouble() {
        return mapping.getAsDouble();
    }

    @Override
    public IMentionable getAsMentionable() {
        return mapping.getAsMentionable();
    }

    @Override
    public Member getAsMember() {
        return mapping.getAsMember();
    }

    @Override
    public User getAsUser() {
        return mapping.getAsUser();
    }

    @Override
    public Role getAsRole() {
        return mapping.getAsRole();
    }

    @Override
    public GuildChannelUnion getAsChannel() {
        return mapping.getAsChannel();
    }

    @Override
    public ChannelType getChannelType() {
        return mapping.getChannelType();
    }
}
