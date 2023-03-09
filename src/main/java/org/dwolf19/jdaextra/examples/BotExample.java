package org.dwolf19.jdaextra.examples;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import org.dwolf19.jdaextra.CommandClientBuilder;

public class BotExample {

    public static void main(String[] args) {
        final String TOKEN = "<...>";

        CommandClientBuilder client = new CommandClientBuilder()
                .setWhenMentionedOr("!")
                .addPrefixCommands(new PrefixCommandExample())
                .addSlashCommands(new SlashCommandExample())
                .addHybridCommands(new HybridCommandExample());

        JDA jda = JDABuilder.createDefault(TOKEN)
                .addEventListeners(client.build())
                .build();
    }

}
