using niuz.application.entities;
using niuz.application.events;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class ArticleService
    {
        private readonly IArticleRepository articles;
        private readonly IEventPublisher publisher;

        public ArticleService(IArticleRepository articles, IEventPublisher publisher)
        {
            this.articles = articles;
            this.publisher = publisher;
        }

        public void Submit(string articleId, string authorId, string headline)
        {
            articles.Save(new Article(articleId, authorId, headline));
            publisher.Publish(new ArticleSubmitted(authorId, headline));
        }
    }
}