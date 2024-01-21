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
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Add OptionType checks to getters (and exception throwing)
/**
 * Name/value mapping of {@link com.dwolfnineteen.jdaextra.events.SlashCommandEvent SlashCommandEvent} option.
 * This is semantic container for the original
 * {@link net.dv8tion.jda.api.interactions.commands.OptionMapping OptionMapping}.
 */
public class SlashOptionMapping implements CommandOptionMapping {
    private final OptionMapping mapping;

    /**
     * Construct new {@link com.dwolfnineteen.jdaextra.options.mappings.SlashOptionMapping SlashOptionMapping}.
     *
     * @param mapping The original {@link net.dv8tion.jda.api.interactions.commands.OptionMapping OptionMapping}.
     * @see com.dwolfnineteen.jdaextra.options.mappings.SlashOptionMapping SlashOptionMapping
     */
    public SlashOptionMapping(@NotNull OptionMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    @NotNull
    public OptionType getType() {
        return mapping.getType();
    }

    @Override
    @NotNull
    public String getName() {
        return mapping.getName();
    }

    @Override
    @NotNull
    public String getAsString() {
        return mapping.getAsString();
    }

    /**
     * Value for this option as {@link net.dv8tion.jda.api.entities.Message.Attachment Message.Attachment} instance.
     *
     * @return Value as {@link net.dv8tion.jda.api.entities.Message.Attachment Message.Attachment} instance.
     */
    @NotNull
    public Message.Attachment getAsAttachment() {
        return mapping.getAsAttachment();
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
    @NotNull
    public IMentionable getAsMentionable() {
        return mapping.getAsMentionable();
    }

    @Override
    @Nullable
    public Member getAsMember() {
        return mapping.getAsMember();
    }

    @Override
    @NotNull
    public User getAsUser() {
        return mapping.getAsUser();
    }

    @Override
    @NotNull
    public Role getAsRole() {
        return mapping.getAsRole();
    }

    @Override
    @NotNull
    public GuildChannelUnion getAsChannel() {
        return mapping.getAsChannel();
    }

    @Override
    @NotNull
    public ChannelType getChannelType() {
        return mapping.getChannelType();
    }
}
