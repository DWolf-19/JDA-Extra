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
import com.dwolfnineteen.jdaextra.options.mappings.PrefixOptionMapping;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.GuildMessageChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.sticker.StickerSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

// TODO: Add some interfaces from JDA to signature
/**
 * Prefix command event.
 */
public class PrefixCommandEvent implements CommandEvent {
    private final MessageReceivedEvent event;
    private final JDAExtra jdaExtra;
    private final String prefix;
    private final String name;
    private final String description;
    private final List<PrefixOptionMapping> options;

    /**
     * Construct new {@link PrefixCommandEvent}.
     *
     * @param event The source {@link MessageReceivedEvent}.
     * @param jdaExtra The {@link JDAExtra} instance.
     * @param prefix Trigger (prefix/mention) from the message.
     * @param name The command name.
     * @param description The command description.
     * @param options {@link List} of {@link PrefixOptionMapping}.
     */
    public PrefixCommandEvent(@NotNull MessageReceivedEvent event,
                              @NotNull JDAExtra jdaExtra,
                              @NotNull String prefix,
                              @NotNull String name,
                              @Nullable String description,
                              @NotNull List<PrefixOptionMapping> options) {
        this.event = event;
        this.jdaExtra = jdaExtra;
        this.prefix = prefix;
        this.name = name;
        this.description = description;
        this.options = options;
    }

    @Override
    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }

    @Override
    @NotNull
    public JDA getJDA() {
        return event.getJDA();
    }

    /**
     * Trigger (prefix/mention) from the message.
     *
     * @return The trigger.
     */
    @NotNull
    public String getPrefix() {
        return prefix;
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * Command options (option mappings).
     *
     * @return {@link List} of {@link PrefixOptionMapping}.
     */
    @NotNull
    public List<PrefixOptionMapping> getOptions() {
        return options;
    }

    // TODO: Add getOptionByName() and various others utils for options

    @NotNull
    public Message getMessage() {
        return event.getMessage();
    }

    @Override
    @NotNull
    public User getUser() {
        return event.getAuthor();
    }

    @Nullable
    public Member getMember() {
        return event.getMember();
    }

    public boolean isWebhookMessage() {
        return event.isWebhookMessage();
    }

    @Override
    @NotNull
    public Guild getGuild() {
        if (isFromGuild()) {
            return event.getGuild();
        }

        // Override IllegalStateException("This message event did not happen in a guild")
        throw new RuntimeException("This command is not called on guild");
    }

    public boolean isFromGuild() {
        return event.isFromGuild();
    }

    @Override
    @NotNull
    public MessageChannelUnion getChannel() {
        return event.getChannel();
    }

    @NotNull
    public GuildMessageChannelUnion getGuildChannel() {
        if (!isFromGuild()) {
            // Override IllegalStateException("This message event did not happen in a guild")
            throw new RuntimeException("This command is not called on guild");
        }

        return event.getGuildChannel();
    }

    @NotNull
    public ChannelType getChannelType() {
        return event.getChannelType();
    }

    public boolean isFromType(@NotNull ChannelType type) {
        return event.isFromType(type);
    }

    public boolean isFromThread() {
        return event.isFromThread();
    }

    @NotNull
    public String getMessageId() {
        return event.getMessageId();
    }

    public long getMessageIdLong() {
        return event.getMessageIdLong();
    }

    @NotNull
    public String getJumpUrl() {
        return event.getJumpUrl();
    }

    /**
     * Shortcut for {@link net.dv8tion.jda.api.entities.Message#reply(CharSequence) getMessage().reply(CharSequence)}.
     *
     * @param content The reply content.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction reply(@NotNull CharSequence content) {
        return event.getMessage().reply(content);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#reply(MessageCreateData) getMessage().reply(MessageCreateData)}.
     *
     * @param data The {@link MessageCreateData} to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction reply(@NotNull MessageCreateData data) {
        return event.getMessage().reply(data);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyEmbeds(Collection) getMessage().replyEmbeds(Collection)}.
     *
     * @param embeds The {@link Collection} of {@link MessageEmbed} to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyEmbeds(@NotNull Collection<? extends MessageEmbed> embeds) {
        return event.getMessage().replyEmbeds(embeds);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyEmbeds(MessageEmbed, MessageEmbed...) getMessage().replyEmbeds(MessageEmbed, MessageEmbed...)}.
     *
     * @param embed The {@link MessageEmbed} to reply.
     * @param other Any addition {@link MessageEmbed}s to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyEmbeds(@NotNull MessageEmbed embed, @NotNull MessageEmbed... other) {
        return event.getMessage().replyEmbeds(embed, other);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyComponents(Collection) getMessage().replyComponents(Collection)}.
     *
     * @param components The {@link Collection} of {@link LayoutComponent} to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyComponents(@NotNull Collection<? extends LayoutComponent> components) {
        return event.getMessage().replyComponents(components);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyComponents(LayoutComponent, LayoutComponent...) getMessage().replyComponents(LayoutComponent, LayoutComponent...)}.
     *
     * @param component The {@link LayoutComponent} to reply.
     * @param other Any addition {@link LayoutComponent}s to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyComponents(@NotNull LayoutComponent component, @NotNull LayoutComponent... other) {
        return event.getMessage().replyComponents(component, other);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyFiles(Collection) getMessage().replyFiles(Collection)}.
     *
     * @param files The {@link Collection} of {@link FileUpload} to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyFiles(@NotNull Collection<? extends FileUpload> files) {
        return event.getMessage().replyFiles(files);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyFiles(FileUpload...) getMessage().replyFiles(FileUpload...)}.
     *
     * @param files The {@link FileUpload}s to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyFiles(@NotNull FileUpload... files) {
        return event.getMessage().replyFiles(files);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyFormat(String, Object...) getMessage().replyFormat(String, Object...)}.
     *
     * @param format The format string.
     * @param args The arguments to use in the format string.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyFormat(@NotNull String format, @NotNull Object... args) {
        return event.getMessage().replyFormat(format, args);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyStickers(Collection) getMessage().replyStickers(Collection)}.
     *
     * @param stickers The {@link Collection} of {@link StickerSnowflake} (1-3) to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyStickers(@NotNull Collection<? extends StickerSnowflake> stickers) {
        return event.getMessage().replyStickers(stickers);
    }

    /**
     * Shortcut for
     * {@link net.dv8tion.jda.api.entities.Message#replyStickers(StickerSnowflake...) getMessage().replyStickers(StickerSnowflake...)}.
     *
     * @param stickers The {@link StickerSnowflake}s (1-3) to reply.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction replyStickers(@NotNull StickerSnowflake... stickers) {
        return event.getMessage().replyStickers(stickers);
    }

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
