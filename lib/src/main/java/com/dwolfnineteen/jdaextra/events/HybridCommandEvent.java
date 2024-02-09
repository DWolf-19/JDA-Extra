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
package com.dwolfnineteen.jdaextra.events;

import com.dwolfnineteen.jdaextra.JDAExtra;
import com.dwolfnineteen.jdaextra.exceptions.InvalidHybridEventException;
import com.dwolfnineteen.jdaextra.options.mappings.HybridOptionMapping;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import net.dv8tion.jda.api.utils.messages.MessageCreateRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

// TODO: Add some interfaces from JDA to signature
/**
 * Hybrid command event.
 */
public class HybridCommandEvent implements CommandEvent {
    private final CommandEvent event;
    private final List<HybridOptionMapping> options;

    /**
     * Construct new {@link HybridCommandEvent}.
     *
     * @param event The {@link CommandEvent} instance.
     */
    public HybridCommandEvent(@NotNull CommandEvent event, @NotNull List<HybridOptionMapping> options) {
        this.event = event;
        this.options = options;
    }

    /**
     * The source {@link CommandEvent} for this hybrid event.
     *
     * @return The {@link CommandEvent}.
     */
    @NotNull
    public CommandEvent getSourceEvent() {
        return event;
    }

    @Override
    @NotNull
    public JDAExtra getJDAExtra() {
        return event.getJDAExtra();
    }

    @Override
    @NotNull
    public JDA getJDA() {
        return event.getJDA();
    }

    @Override
    @NotNull
    public String getName() {
        return event.getName();
    }

    @Override
    @NotNull
    public String getDescription() {
        return event.getDescription();
    }

    @NotNull
    public List<HybridOptionMapping> getOptions() {
        return options;
    }

    // TODO: Add getOptionByName() and various others utils for options

    @Override
    @Nullable
    public Guild getGuild() {
        return event.getGuild();
    }

    @Override
    @Nullable
    public MessageChannelUnion getChannel() {
        return event.getChannel();
    }

    @Override
    @NotNull
    public User getUser() {
        return event.getUser();
    }

    // TODO: Replace with HybridReplyAction
//    @NotNull
//    public MessageCreateRequest<?> reply(@NotNull String content) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).reply(content);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).reply(content);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> reply(@NotNull MessageCreateData data) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).reply(data);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).reply(data);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> replyEmbeds(@NotNull Collection<? extends MessageEmbed> embeds) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).replyEmbeds(embeds);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).replyEmbeds(embeds);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> replyEmbeds(@NotNull MessageEmbed embed, @NotNull MessageEmbed... other) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).replyEmbeds(embed, other);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).replyEmbeds(embed, other);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> replyComponents(@NotNull Collection<? extends LayoutComponent> components) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).replyComponents(components);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).replyComponents(components);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> replyComponents(@NotNull LayoutComponent component,
//                                                   @NotNull LayoutComponent... other) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).replyComponents(component, other);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).replyComponents(component, other);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> replyFiles(@NotNull Collection<? extends FileUpload> files) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).replyFiles(files);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).replyFiles(files);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> replyFiles(@NotNull FileUpload... files) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).replyFiles(files);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).replyFiles(files);
//        }
//
//        throw new InvalidHybridEventException();
//    }
//
//    @NotNull
//    public MessageCreateRequest<?> replyFormat(@NotNull String format, @NotNull Object... args) {
//        if (event instanceof SlashCommandEvent) {
//            return ((SlashCommandEvent) event).replyFormat(format, args);
//        } else if (event instanceof PrefixCommandEvent) {
//            return ((PrefixCommandEvent) event).replyFormat(format, args);
//        }
//
//        throw new InvalidHybridEventException();
//    }

    @Override
    public long getResponseNumber() {
        return event.getResponseNumber();
    }

    @Override
    @Nullable
    public DataObject getRawData() {
        return event.getRawData();
    }
}
