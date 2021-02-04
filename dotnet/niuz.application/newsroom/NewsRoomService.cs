using niuz.application.commands;
using niuz.application.events;

namespace niuz.application.newsroom
{
    public class NewsRoomService : ICommandHandler<SubmitArticle>
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

        public void Publish(string articleId)
        {
            var article = articles.GetByArticleId(articleId);
            var author = authors.GetByAuthorId(article.AuthorId);

            publisher.Publish(new ArticlePublished(author.Id, author.Name, article.Headline));
        }

        public void Handle(SubmitArticle command)
        {
            articles.Save(new Article(command.ArticleId, command.AuthorId, command.Headline));
            publisher.Publish(new ArticleSubmitted(command.AuthorId, command.Headline));
        }
    }
}