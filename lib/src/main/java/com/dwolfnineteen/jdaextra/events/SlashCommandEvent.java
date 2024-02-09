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
import com.dwolfnineteen.jdaextra.options.mappings.SlashOptionMapping;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

// TODO: Add some interfaces from JDA to signature
/**
 * Slash command event.
 */
public class SlashCommandEvent implements CommandEvent {
    private final SlashCommandInteractionEvent event;
    private final JDAExtra jdaExtra;
    private final List<SlashOptionMapping> options;
    private final String description;

    /**
     * Construct new {@link SlashCommandEvent}.
     *
     * @param event The source {@link SlashCommandInteractionEvent}.
     * @param jdaExtra The {@link JDAExtra} instance.
     */
    public SlashCommandEvent(@NotNull SlashCommandInteractionEvent event, 
                             @NotNull JDAExtra jdaExtra,
                             @NotNull List<SlashOptionMapping> options,
                             @NotNull String description) {
        this.event = event;
        this.jdaExtra = jdaExtra;
        this.options = options;
        this.description = description;
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

    @Override
    @NotNull
    public String getName() {
        return event.getName();
    }

    @Override
    @NotNull
    public String getDescription() {
        return description;
    }

    @NotNull
    public List<SlashOptionMapping> getOptions() {
        return options;
    }

    // TODO: Add getOptionByName() and various others utils for options

    @NotNull
    public SlashCommandInteraction getInteraction() {
        return event.getInteraction();
    }

    @NotNull
    public Command.Type getCommandType() {
        return event.getCommandType();
    }

    public long getCommandIdLong() {
        return event.getCommandIdLong();
    }

    public boolean isGuildCommand() {
        return event.isGuildCommand();
    }

    public boolean isGlobalCommand() {
        return event.isGlobalCommand();
    }

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

    public long getChannelIdLong() {
        return event.getChannelIdLong();
    }

    @NotNull
    public DiscordLocale getGuildLocale() {
        return event.getGuildLocale();
    }

    @NotNull
    public DiscordLocale getUserLocale() {
        return event.getUserLocale();
    }

    @Nullable
    public Member getMember() {
        return event.getMember();
    }

    @Override
    @NotNull
    public User getUser() {
        return event.getUser();
    }

    @NotNull
    public InteractionHook getHook() {
        return event.getHook();
    }

    @NotNull
    public ReplyCallbackAction deferReply() {
        return event.deferReply();
    }

    @NotNull
    public ModalCallbackAction replyModal(@NotNull Modal modal) {
        return event.replyModal(modal);
    }

    @NotNull
    public ReplyCallbackAction deferReply(boolean ephemeral) {
        return event.deferReply(ephemeral);
    }

    @NotNull
    public ReplyCallbackAction reply(@NotNull String content) {
        return event.reply(content);
    }

    @NotNull
    public ReplyCallbackAction reply(@NotNull MessageCreateData data) {
        return event.reply(data);
    }

    @NotNull
    public ReplyCallbackAction replyEmbeds(@NotNull Collection<? extends MessageEmbed> embeds) {
        return event.replyEmbeds(embeds);
    }

    @NotNull
    public ReplyCallbackAction replyEmbeds(@NotNull MessageEmbed embed, @NotNull MessageEmbed... other) {
        return event.replyEmbeds(embed, other);
    }

    @NotNull
    public ReplyCallbackAction replyComponents(@NotNull Collection<? extends LayoutComponent> components) {
        return event.replyComponents(components);
    }

    @NotNull
    public ReplyCallbackAction replyComponents(@NotNull LayoutComponent component, @NotNull LayoutComponent... other) {
        return event.replyComponents(component, other);
    }

    @NotNull
    public ReplyCallbackAction replyFiles(@NotNull Collection<? extends FileUpload> files) {
        return event.replyFiles(files);
    }

    @NotNull
    public ReplyCallbackAction replyFiles(@NotNull FileUpload... files) {
        return event.replyFiles(files);
    }

    @NotNull
    public ReplyCallbackAction replyFormat(@NotNull String format, @NotNull Object... args) {
        return event.replyFormat(format, args);
    }

    public long getIdLong() {
        return event.getIdLong();
    }

    public boolean isAcknowledged() {
        return event.isAcknowledged();
    }

    @NotNull
    public String getToken() {
        return event.getToken();
    }

    public int getTypeRaw() {
        return event.getTypeRaw();
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
