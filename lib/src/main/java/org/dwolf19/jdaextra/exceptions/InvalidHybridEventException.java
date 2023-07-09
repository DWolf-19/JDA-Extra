package org.dwolf19.jdaextra.exceptions;

public class InvalidHybridEventException extends CommandException {
    public InvalidHybridEventException() {
        super("Correct source event wasn't passed to HybridCommandEvent");
    }
}
