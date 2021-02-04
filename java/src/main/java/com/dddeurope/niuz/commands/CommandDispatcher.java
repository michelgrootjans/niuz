package com.dddeurope.niuz.commands;

import com.dddeurope.niuz.hr.HireAuthor;

import java.util.Arrays;
import java.util.List;

public class CommandDispatcher {
    private final List<CommandHandler> handlers;

    public CommandDispatcher(CommandHandler<HireAuthor>... handlers) {
        this.handlers = Arrays.asList(handlers);
    }

    public void dispatch(Object command) {
        handlers.stream()
                .filter(handler -> handler.canHandle(command))
                .forEach(handler -> handler.handle(command));
    }
}
