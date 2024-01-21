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

/**
 * Basic interface for name/value mappings of {@link com.dwolfnineteen.jdaextra.events.CommandEvent CommandEvent}
 * options.
 */
public interface CommandOptionMapping {
    /**
     * The {@link net.dv8tion.jda.api.interactions.commands.OptionType OptionType} for this option.
     *
     * @return The {@link net.dv8tion.jda.api.interactions.commands.OptionType OptionType}.
     */
    OptionType getType();

    /**
     * The name for this option.
     *
     * @return The name.
     */
    String getName();

    /**
     * The {@link String} representation of this option.
     *
     * @return The {@link String} representation.
     */
    String getAsString();

    /**
     * Value for this option as a {@code boolean}.
     *
     * @return Value as a {@code boolean}.
     */
    boolean getAsBoolean();

    /**
     * Value for this option as a {@code long}.
     *
     * @return Value as a {@code long}.
     */
    long getAsLong();

    /**
     * Value for this option as an {@code int}.
     *
     * @return Value as an {@code int}.
     */
    int getAsInt();

    /**
     * Value for this option as a {@code double}.
     *
     * @return Value as a {@code double}.
     */
    double getAsDouble();

    /**
     * Value for this option as {@link net.dv8tion.jda.api.entities.IMentionable IMentionable} instance.
     *
     * @return Value as {@link net.dv8tion.jda.api.entities.IMentionable IMentionable} instance.
     */
    IMentionable getAsMentionable();

    /**
     * Value for this option as {@link net.dv8tion.jda.api.entities.Member Member} instance.
     *
     * @return Value as {@link net.dv8tion.jda.api.entities.Member Member} instance.
     */
    Member getAsMember();

    /**
     * Value for this option as {@link net.dv8tion.jda.api.entities.User User} instance.
     *
     * @return Value as {@link net.dv8tion.jda.api.entities.User User} instance.
     */
    User getAsUser();

    /**
     * Value for this option as {@link net.dv8tion.jda.api.entities.Role Role} instance.
     *
     * @return Value as {@link net.dv8tion.jda.api.entities.Role Role} instance.
     */
    Role getAsRole();

    /**
     * Value for this option as
     * {@link net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion GuildChannelUnion} instance.
     *
     * @return Value as
     * {@link net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion GuildChannelUnion} instance.
     */
    GuildChannelUnion getAsChannel();

    /**
     * The {@link net.dv8tion.jda.api.entities.channel.ChannelType ChannelType} for
     * {@link com.dwolfnineteen.jdaextra.options.mappings.CommandOptionMapping#getAsChannel CommandOptionMapping#getAsChannel}
     * result (this option value).
     *
     * @return The {@link net.dv8tion.jda.api.entities.channel.ChannelType ChannelType}.
     */
    ChannelType getChannelType();
}
