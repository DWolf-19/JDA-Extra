package com.dwolfnineteen.jdaextra.exceptions;

public class CommandAnnotationNotFoundException extends CommandException {
    public CommandAnnotationNotFoundException() {
        super("Extra[...]Command annotation wasn't found for command class");
    }
}
