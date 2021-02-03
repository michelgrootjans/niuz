using niuz.application.entities;
using niuz.application.events;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class PublishingService
    {
        private readonly IAuthorRepository authors;
        private readonly IArticleRepository articles;
        private readonly IPaymentRepository payments;
        private readonly IEventPublisher publisher;

        public PublishingService(IAuthorRepository authors, IArticleRepository articles, IPaymentRepository payments, IEventPublisher publisher)
        {
            this.authors = authors;
            this.articles = articles;
            this.payments = payments;
            this.publisher = publisher;
        }

        public void Publish(string articleId)
        {
            var article = articles.GetByArticleId(articleId);
            var author = authors.GetByAuthorId(article.AuthorId);

            publisher.Publish(new ArticlePublished(article.Headline, author.Name));
            if (author.PaysByPublication)
            {
                payments.Save(new Payment(author.Rate, author.BankAccount, author.Name, article.Headline));    
            }
        }
    }
}