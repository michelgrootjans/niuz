using System;

namespace niuz.application.events
{
    public interface ITopic
    {
        void Subscribe<T>(Action<T> publish) where T: class;
    }
}