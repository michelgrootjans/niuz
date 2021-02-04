using niuz.application.events;

namespace niuz.application.hr
{
    public class AuthorService
    {
        private readonly IEventPublisher publisher;

        public AuthorService(IEventPublisher publisher)
        {
            this.publisher = publisher;
        }

        public void Hire(HireAuthor command)
        {
            publisher.Publish(new ContractSigned(command.AuthorId, command.AuthorName, command.ContractType, command.Rate, command.BankAccount));
        }
    }
}