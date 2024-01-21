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
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.sticker.StickerSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

// TODO: Convert PrefixCommandEvent to container for MessageReceivedEvent
/**
 * Prefix command event.
 */
public class PrefixCommandEvent extends MessageReceivedEvent implements CommandEvent {
    private final JDAExtra jdaExtra;
    private final String prefix;
    private final String name;
    private final String description;
    private final List<PrefixOptionMapping> options;

    // TODO: Rename prefix to trigger
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
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());

        this.jdaExtra = jdaExtra;
        this.prefix = prefix;
        this.name = name;
        this.description = description;
        this.options = options;
    }

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
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

    // TODO: Definition and javadoc will be in CommandEvent
    @NotNull
    public String getName() {
        return name;
    }

    // TODO: Move definition to CommandEvent
    /**
     * The command description.
     *
     * @return The description.
     */
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

    // TODO: Add getMessage() method

    /**
     * Shortcut for {@link net.dv8tion.jda.api.entities.Message#reply(CharSequence) getMessage().reply(CharSequence)}.
     *
     * @param content The reply content.
     * @return The {@link MessageCreateAction}.
     */
    @NotNull
    public MessageCreateAction reply(@NotNull CharSequence content) {
        return getMessage().reply(content);
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
        return getMessage().reply(data);
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
        return getMessage().replyComponents(components);
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
        return getMessage().replyComponents(component, other);
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
        return getMessage().replyEmbeds(embeds);
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
        return getMessage().replyEmbeds(embed, other);
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
        return getMessage().replyFiles(files);
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
        return getMessage().replyFiles(files);
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
    public MessageCreateAction replyFormat(@NotNull String format, Object... args) {
        return getMessage().replyFormat(format, args);
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
        return getMessage().replyStickers(stickers);
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
        return getMessage().replyStickers(stickers);
    }
}
