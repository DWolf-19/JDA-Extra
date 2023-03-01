package org.dwolf19.jdaextra.events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import org.dwolf19.jdaextra.CommandClient;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HybridCommandEvent<E extends Event> {

    private final E event;

    public HybridCommandEvent(@NotNull E event) {
        this.event = event;
    }

    @NotNull
    public E getSourceEvent() {
        return event;
    }

    // --- Wrappers ---

    @NotNull
    public CommandClient getClient() {
        return event.getClient();
    }

    @NotNull
    public JDA getJDA() {
        return event.getJDA();
    }

    @Nullable
    public Guild getGuild() {
        return event.getGuild();
    }

    @NotNull
    public MessageChannelUnion getChannel() {
        return event.getChannel();
    }

    @Nullable
    public Member getMember() {
        return event.getMember();
    }

    @NotNull
    public String getName() {
        return event.getName();
    }

    @NotNull
    public ChannelType getChannelType() {
        return event.getChannelType();
    }

    @NotNull
    public RestAction<?> reply(String content) {
        return event.reply(content);
    }

    @NotNull
    public RestAction<?> reply(MessageCreateData message) {
        return event.reply(message);
    }

}
