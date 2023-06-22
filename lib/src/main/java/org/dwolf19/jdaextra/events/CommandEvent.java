package org.dwolf19.jdaextra.events;

import org.dwolf19.jdaextra.CommandClient;

public interface CommandEvent {
    CommandClient getClient();
}
