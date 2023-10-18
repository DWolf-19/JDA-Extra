/*
Copyright (c) 2023 DWolf Nineteen & The JDA-Extra contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
public class PrefixCommandEvent extends MessageReceivedEvent implements CommandEvent {
    private final JDAExtra jdaExtra;
    private final String prefix;
    private final String name;
    private final String description;
    private final List<PrefixOptionMapping> options;

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

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @NotNull
    public List<PrefixOptionMapping> getOptions() {
        return options;
    }

    @NotNull
    public MessageCreateAction reply(@NotNull CharSequence content) {
        return getMessage().reply(content);
    }

    @NotNull
    public MessageCreateAction reply(@NotNull MessageCreateData data) {
        return getMessage().reply(data);
    }

    @NotNull
    public MessageCreateAction replyComponents(@NotNull Collection<? extends LayoutComponent> components) {
        return getMessage().replyComponents(components);
    }

    @NotNull
    public MessageCreateAction replyComponents(@NotNull LayoutComponent component, @NotNull LayoutComponent... other) {
        return getMessage().replyComponents(component, other);
    }

    @NotNull
    public MessageCreateAction replyEmbeds(@NotNull Collection<? extends MessageEmbed> embeds) {
        return getMessage().replyEmbeds(embeds);
    }

    @NotNull
    public MessageCreateAction replyEmbeds(@NotNull MessageEmbed embed, @NotNull MessageEmbed... other) {
        return getMessage().replyEmbeds(embed, other);
    }

    @NotNull
    public MessageCreateAction replyFiles(@NotNull Collection<? extends FileUpload> files) {
        return getMessage().replyFiles(files);
    }

    @NotNull
    public MessageCreateAction replyFiles(@NotNull FileUpload... files) {
        return getMessage().replyFiles(files);
    }

    @NotNull
    public MessageCreateAction replyFormat(@NotNull String format, Object... args) {
        return getMessage().replyFormat(format, args);
    }

    @NotNull
    public MessageCreateAction replyStickers(@NotNull Collection<? extends StickerSnowflake> stickers) {
        return getMessage().replyStickers(stickers);
    }

    @NotNull
    public MessageCreateAction replyStickers(@NotNull StickerSnowflake... stickers) {
        return getMessage().replyStickers(stickers);
    }
}
