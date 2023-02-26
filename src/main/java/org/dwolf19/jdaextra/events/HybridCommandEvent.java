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

public abstract class HybridCommandEvent<E extends Event> {

    private final E event;

    public HybridCommandEvent(E event) {
        this.event = event;
    }

    public E getSourceEvent() {
        return event;
    }

    // --- Wrappers ---

    public CommandClient getClient() {
        return event.getClient();
    }

    public JDA getJDA() {
        return event.getJDA();
    }

    public Guild getGuild() {
        return event.getGuild();
    }

    public MessageChannelUnion getChannel() {
        return event.getChannel();
    }

    public Member getMember() {
        return event.getMember();
    }

    public String getName() {
        return event.getName();
    }

    public ChannelType getChannelType() {
        return event.getChannelType();
    }

    public RestAction<?> reply(String content) {
        return event.reply(content);
    }

    public RestAction<?> reply(MessageCreateData message) {
        return event.reply(message);
    }

}
