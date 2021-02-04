using System.Linq;

namespace niuz.application.commands
{
    public class CommandDispatcher
    {
        private readonly object[] handlers;

        public CommandDispatcher(params object[] handlers)
        {
            this.handlers = handlers;
        }

        public void Dispatch<T>(T command)
        {
            handlers
                .OfType<ICommandHandler<T>>()
                .First()
                .Handle(command);
            
        }
    }
}