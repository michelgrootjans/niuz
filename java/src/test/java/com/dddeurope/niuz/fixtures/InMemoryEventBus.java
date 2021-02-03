package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.events.EventPublisher;
import com.dddeurope.niuz.events.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InMemoryEventBus implements EventPublisher, Topic {
    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public <T> void subscribe(Class<T> eventType, Consumer<T> handler) {
        subscriptions.add(new Subscription<>(eventType, handler));
    }

    @Override
    public void publish(Object event) {
        for (Subscription s : subscriptions) s.handle(event);
    }

    private static class Subscription<T> {
        private final Class<T> eventType;
        private final Consumer<T> handler;

        public Subscription(Class<T> eventType, Consumer<T> handler) {
            this.eventType = eventType;
            this.handler = handler;
        }

        public void handle(Object event) {
            if (eventType.equals(event.getClass())) {
                handler.accept((T) event);
            }
        }
    }}
