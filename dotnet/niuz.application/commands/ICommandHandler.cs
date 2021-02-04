namespace niuz.application.commands
{
    public interface ICommandHandler<in T>
    {
        void Handle(T command);
    }
}