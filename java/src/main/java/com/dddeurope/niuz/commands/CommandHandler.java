package com.dddeurope.niuz.commands;

public interface CommandHandler<T> {
    boolean canHandle(Object command);
    void handle(T command);
}
