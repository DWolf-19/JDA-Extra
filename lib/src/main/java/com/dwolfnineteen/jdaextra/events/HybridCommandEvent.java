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
import org.jetbrains.annotations.NotNull;

/**
 * Hybrid command event.
 */
public class HybridCommandEvent implements CommandEvent {
    private final CommandEvent event;

    /**
     * Construct new {@link com.dwolfnineteen.jdaextra.events.HybridCommandEvent HybridCommandEvent}.
     *
     * @param event The {@link com.dwolfnineteen.jdaextra.events.CommandEvent CommandEvent} instance.
     */
    public HybridCommandEvent(@NotNull CommandEvent event) {
        this.event = event;
    }

    /**
     * The source {@link com.dwolfnineteen.jdaextra.events.CommandEvent CommandEvent} for this hybrid event.
     *
     * @return The {@link com.dwolfnineteen.jdaextra.events.CommandEvent CommandEvent}.
     */
    @NotNull
    public CommandEvent getEvent() {
        return event;
    }

    @Override
    @NotNull
    public JDAExtra getJDAExtra() {
        return event.getJDAExtra();
    }

    // TODO: Move definition to CommandEvent
    /**
     * The command name.
     *
     * @return The name.
     */
    @NotNull
    public String getName() {
        if (event instanceof SlashCommandEvent) {
            return ((SlashCommandEvent) event).getName();
        } else if (event instanceof PrefixCommandEvent) {
            return ((PrefixCommandEvent) event).getName();
        }

        throw new InvalidHybridEventException();
    }

    // TODO: Write other "unified" methods
}
