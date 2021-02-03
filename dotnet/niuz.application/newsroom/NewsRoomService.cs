using niuz.application.events;

namespace niuz.application.newsroom
{
    public class NewsRoomService
    {
        private readonly IAuthorRepository authors;
        private readonly IArticleRepository articles;
        private readonly IEventPublisher publisher;

        public NewsRoomService(IAuthorRepository authors, IArticleRepository articles, ITopic topic, IEventPublisher publisher)
        {
            this.authors = authors;
            this.articles = articles;
            this.publisher = publisher;
            topic.Subscribe<ContractSigned>(Onboard);
        }

        private void Onboard(ContractSigned @event)
        {
            authors.Save(new Author(@event.AuthorId, @event.AuthorName));
        }

        public void Submit(string articleId, string authorId, string headline)
        {
            articles.Save(new Article(articleId, authorId, headline));
            publisher.Publish(new ArticleSubmitted(authorId, headline));
        }

        public void Publish(string articleId)
        {
            var article = articles.GetByArticleId(articleId);
            var author = authors.GetByAuthorId(article.AuthorId);

            publisher.Publish(new ArticlePublished(author.Id, author.Name, article.Headline));
        }
    }
}