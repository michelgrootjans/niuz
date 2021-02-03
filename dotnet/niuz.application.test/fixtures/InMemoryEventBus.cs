using System;
using System.Collections.Generic;
using niuz.application.events;

namespace niuz.application.fixtures
{
    public class InMemoryEventBus : ITopic, IEventPublisher
    {
        private readonly List<IEventSubscription> subscriptions = new List<IEventSubscription>();

        public void Subscribe<T>(Action<T> handler) where T : class
        {
            subscriptions.Add(new EventSubscription<T>(handler));
        }

        public void Publish(object @event)
        {
            foreach (var subscription in subscriptions) subscription.Handle(@event);
        }

        private interface IEventSubscription
        {
            void Handle(object @event);
        }

        private class EventSubscription<T> : IEventSubscription where T : class
        {
            private readonly Action<T> handler;

            public EventSubscription(Action<T> handler)
            {
                this.handler = handler;
            }

            public void Handle(object @event)
            {
                if (@event.GetType() == typeof(T)) handler(@event as T);
            }
        }
    }
}