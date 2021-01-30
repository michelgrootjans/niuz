using niuz.application.entities;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class PublishingService
    {
        private readonly IAuthorRepository authors;
        private readonly IArticleRepository articles;
        private readonly ITeaserRepository teasers;

        private readonly IPaymentRepository payments;

        public PublishingService(IAuthorRepository authors, IArticleRepository articles, ITeaserRepository teasers, IPaymentRepository payments)
        {
            this.authors = authors;
            this.articles = articles;
            this.teasers = teasers;
            this.payments = payments;
        }

        public void Publish(string articleId)
        {
            var article = articles.GetByArticleId(articleId);
            var author = authors.GetByAuthorId(article.AuthorId);

            teasers.Save("homepage", new Teaser(article.Headline, author.Name));
            if (author.PaysByPublication)
            {
                payments.Save(new Payment(author.Rate, author.BankAccount, author.Name, article.Headline));    
            }
        }
    }
}