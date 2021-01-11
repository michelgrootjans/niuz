using niuz.application.entities;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class ArticleService
    {
        private readonly IAuthorRepository authors;
        private readonly IArticleRepository articles;
        private readonly IPaymentRepository payments;

        public ArticleService(IAuthorRepository authors, IArticleRepository articles, IPaymentRepository payments)
        {
            this.authors = authors;
            this.articles = articles;
            this.payments = payments;
        }

        public void Submit(string articleId, string authorId, string headline)
        {
            var article = new Article(articleId, authorId, headline);
            var author = authors.GetByAuthorId(authorId);
            
            articles.Save(article);
            if (author.PaysBySubmission)
            {
                payments.Save(new Payment(50, author.BankAccount, author.Name, article.Headline));
            }
        }
    }
}