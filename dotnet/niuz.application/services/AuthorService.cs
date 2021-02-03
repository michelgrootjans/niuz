using niuz.application.events;
using niuz.application.newsroom;

namespace niuz.application.services
{
    public class AuthorService
    {
        private readonly IAuthorRepository authors;
        private readonly IEventPublisher publisher;

        public AuthorService(IAuthorRepository authors, IEventPublisher publisher)
        {
            this.authors = authors;
            this.publisher = publisher;
        }

        public void Hire(string authorId, string authorName, string bankAccount, string contractType, int rate)
        {
            publisher.Publish(new ContractSigned(authorId, authorName, contractType, rate, bankAccount));
        }
    }
}