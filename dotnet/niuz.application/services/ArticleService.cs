using niuz.application.entities;
using niuz.application.events;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class ArticleService
    {
        private readonly IAuthorRepository authors;
        private readonly IArticleRepository articles;
        private readonly IPaymentRepository payments;
        private readonly IEventPublisher publisher;

        public ArticleService(IAuthorRepository authors, IArticleRepository articles, IPaymentRepository payments, IEventPublisher publisher)
        {
            this.authors = authors;
            this.articles = articles;
            this.payments = payments;
            this.publisher = publisher;
        }

        public void Submit(string articleId, string authorId, string headline)
        {
            articles.Save(new Article(articleId, authorId, headline));
            publisher.Publish(new ArticleSubmitted(authorId, headline));
        }
    }
}