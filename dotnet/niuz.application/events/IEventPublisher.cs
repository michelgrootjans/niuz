namespace niuz.application.events
{
    public interface IEventPublisher
    {
        void Publish(object @event);
    }
}