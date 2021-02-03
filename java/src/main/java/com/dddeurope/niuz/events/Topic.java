package com.dddeurope.niuz.events;

import java.util.function.Consumer;

public interface Topic {
    <T> void subscribe(Class<T> eventType, Consumer<T> handler);
}
