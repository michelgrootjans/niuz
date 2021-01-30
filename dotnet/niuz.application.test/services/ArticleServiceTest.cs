using niuz.application.entities;
using niuz.application.repositories;
using NSubstitute;
using Xunit;

namespace niuz.application.services
{
    public class ArticleServiceTest
    {
        private readonly IAuthorRepository authors;
        private readonly IArticleRepository articles;
        private readonly IPaymentRepository payments;
        private readonly ArticleService articleService;

        public ArticleServiceTest()
        {
            authors = Substitute.For<IAuthorRepository>();
            articles = Substitute.For<IArticleRepository>();
            payments = Substitute.For<IPaymentRepository>();
            articleService = new ArticleService(authors, articles, payments);
        }

        [Fact]
        public void SubmitArticleAndPay()
        {
            authors.GetByAuthorId("author-1").Returns(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50));

            articleService.Submit("article-1", "author-1", "headline");
            
            articles.Received().Save(new Article("article-1", "author-1", "headline"));
            payments.Received().Save(new Payment(50, "123-4567-89", "Freddy Kruger", "headline"));
        }

        [Fact]
        public void SubmitArticleWithoutPay()
        {
            authors.GetByAuthorId("author-1").Returns(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-publication", 100));

            articleService.Submit("article-1", "author-1", "headline");
            
            articles.Received().Save(new Article("article-1", "author-1", "headline"));
            payments.DidNotReceive().Save(Arg.Any<Payment>());
        }
    }
}