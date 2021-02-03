using niuz.application.events;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class PublishingService
    {
        private readonly IAuthorRepository authors;
        private readonly IArticleRepository articles;
        private readonly IEventPublisher publisher;

        public PublishingService(IAuthorRepository authors, IArticleRepository articles, IEventPublisher publisher)
        {
            this.authors = authors;
            this.articles = articles;
            this.publisher = publisher;
        }

        public void Publish(string articleId)
        {
            var article = articles.GetByArticleId(articleId);
            var author = authors.GetByAuthorId(article.AuthorId);

            publisher.Publish(new ArticlePublished(author.Id, author.Name, article.Headline));
        }
    }
}