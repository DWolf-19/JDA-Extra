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
package com.dwolfnineteen.jdaextra.models;

import com.dwolfnineteen.jdaextra.options.data.HybridOptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Hybrid command model.
 * @see com.dwolfnineteen.jdaextra.models.CommandModel CommandModel
 */
public class HybridCommandModel extends CommandModel {
    private String description;
    private List<HybridOptionData> options;

    /**
     * The command description.
     *
     * @return The description.
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * All command options as a {@link java.util.List List}.
     *
     * @return The command options.
     */
    @NotNull
    public List<HybridOptionData> getOptions() {
        return options;
    }

    /**
     * Sets the command description.
     *
     * @param description The command description.
     * @return Current {@link com.dwolfnineteen.jdaextra.models.HybridCommandModel HybridCommandModel} instance,
     * for chaining.
     */
    @NotNull
    public HybridCommandModel setDescription(@NotNull String description) {
        this.description = description;

        return this;
    }

    /**
     * Sets the command options as a {@link java.util.List List}.
     *
     * @param options The command options.
     * @return Current {@link com.dwolfnineteen.jdaextra.models.HybridCommandModel HybridCommandModel} instance,
     * for chaining.
     */
    @NotNull
    public HybridCommandModel setOptions(@NotNull List<HybridOptionData> options) {
        this.options = options;

        return this;
    }
}
