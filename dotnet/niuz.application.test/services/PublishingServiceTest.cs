using niuz.application.entities;
using niuz.application.repositories;
using NSubstitute;
using Xunit;

namespace niuz.application.services
{
    public class PublishingServiceTest
    {
        private readonly IAuthorRepository authors;
        private readonly IPaymentRepository payments;
        private readonly IArticleRepository articles;
        private readonly ITeaserRepository teasers;
        private readonly PublishingService publishingService;

        public PublishingServiceTest()
        {
            authors = Substitute.For<IAuthorRepository>();
            articles = Substitute.For<IArticleRepository>();
            teasers = Substitute.For<ITeaserRepository>();
            payments = Substitute.For<IPaymentRepository>();
            publishingService = new PublishingService(authors, articles, teasers, payments);
        }
        
        [Fact]
        public void PublishArticleWithoutPay()
        {
            authors.GetByAuthorId("author-1").Returns(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50));
            articles.GetByArticleId("article-1").Returns(new Article("article-1", "author-1", "headline"));

            publishingService.Publish("article-1");

            teasers.Received().Save("homepage", new Teaser("headline", "Freddy Kruger"));
            payments.DidNotReceive().Save(Arg.Any<Payment>());
        }

        [Fact]
        public void PublishArticleAndPay()
        {
            authors.GetByAuthorId("author-1").Returns(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-publication", 100));
            articles.GetByArticleId("article-1").Returns(new Article("article-1", "author-1", "headline"));

            publishingService.Publish("article-1");

            teasers.Received().Save("homepage", new Teaser("headline", "Freddy Kruger"));
            payments.Received().Save(new Payment(100, "123-4567-89", "Freddy Kruger", "headline"));
        }
    }
}