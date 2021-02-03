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

        public void Hire(string authorId, string authorName, string bankAccount, string contractType, int rate)
        {
            publisher.Publish(new ContractSigned(authorId, authorName, contractType, rate, bankAccount));
        }
    }
}