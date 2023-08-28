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
package com.dwolfnineteen.jdaextra.prefix;

import com.dwolfnineteen.jdaextra.JDAExtra;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class PrefixCommandParser {
    private final JDAExtra jdaExtra;
    private final MessageReceivedEvent event;
    private PrefixCommandEntity entity;

    public PrefixCommandParser(@NotNull MessageReceivedEvent event, @NotNull JDAExtra jdaExtra) {
        this.event = event;
        this.jdaExtra = jdaExtra;
    }

    @NotNull
    public MessageReceivedEvent getEvent() {
        return event;
    }

    @NotNull
    public PrefixCommandEntity getEntity() {
        if (entity == null)
            throw new NullPointerException();

        return entity;
    }

    @Nullable
    public PrefixCommandParser parse() {
        PrefixCommandEntity entity = new PrefixCommandEntity();

        String content = event.getMessage().getContentRaw();

        String prefix = jdaExtra.getPrefix();
        String mention = event.getJDA().getSelfUser().getAsMention();

        if (!content.startsWith(prefix) || (jdaExtra.isWhenMention() && content.startsWith(mention)))
            return null;

        String trigger = content.startsWith(prefix) ? prefix : mention + " ";
        entity.setTrigger(trigger);

        String[] parts = content.substring(trigger.length()).split(" ");
        entity.setName(parts[0]);

        entity.setArguments(Arrays.copyOfRange(parts, 1, parts.length));

        this.entity = entity;

        return this;
    }
}
