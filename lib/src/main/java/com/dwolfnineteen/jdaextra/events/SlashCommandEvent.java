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
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

// FIXME: SlashCommandEvent#getOptions returns default option mappings
/**
 * Slash command event.
 */
public class SlashCommandEvent extends SlashCommandInteractionEvent implements CommandEvent {
    private final JDAExtra jdaExtra;

    /**
     * Construct new {@link com.dwolfnineteen.jdaextra.events.SlashCommandEvent SlashCommandEvent}.
     *
     * @param event The source
     * {@link net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent SlashCommandInteractionEvent}.
     * @param jdaExtra The {@link com.dwolfnineteen.jdaextra.JDAExtra JDAExtra} instance.
     */
    public SlashCommandEvent(@NotNull SlashCommandInteractionEvent event, @NotNull JDAExtra jdaExtra) {
        super(event.getJDA(), event.getResponseNumber(), event.getInteraction());

        this.jdaExtra = jdaExtra;
    }

    @NotNull
    public JDAExtra getJDAExtra() {
        return jdaExtra;
    }
}
