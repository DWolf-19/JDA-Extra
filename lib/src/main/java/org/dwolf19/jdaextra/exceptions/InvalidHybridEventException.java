package org.dwolf19.jdaextra.exceptions;

public class InvalidHybridEventException extends RuntimeException {
    public InvalidHybridEventException() {
        super("Correct source event wasn't passed to HybridCommandEvent");
    }
}
