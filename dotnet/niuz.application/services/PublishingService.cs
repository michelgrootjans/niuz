using niuz.application.entities;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class PublishingService
    {
        private readonly IArticleRepository articles;
        private readonly IAuthorRepository authors;
        private readonly ITeaserRepository teasers;
        private readonly IPaymentRepository payments;

        public PublishingService(IArticleRepository articles, IAuthorRepository authors, ITeaserRepository teasers,
            IPaymentRepository payments)
        {
            this.articles = articles;
            this.authors = authors;
            this.teasers = teasers;
            this.payments = payments;
        }

        public void PublishArticleById(string articleId)
        {
            var article = articles.GetByArticleId(articleId);
            var author = authors.GetByAuthorId(article.AuthorId);

            teasers.Save("homepage", new Teaser(article.Headline, author.Name));
            payments.Save(new Payment(100, author.BankAccount, author.Name, article.Headline));
        }
    }
}