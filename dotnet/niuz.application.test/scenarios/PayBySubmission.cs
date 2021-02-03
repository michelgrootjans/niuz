using FluentAssertions;
using niuz.application.dtos;
using niuz.application.fixtures;
using niuz.application.services;
using Xunit;

namespace niuz.application.scenarios
{
    public class PayBySubmission
    {
        private readonly AuthorService authorService;
        private readonly ArticleService articleService;
        private readonly TeaserService teaserService;
        private readonly PublishingService publishingService;
        private readonly PaymentService paymentService;

        public PayBySubmission()
        {
            var authors = new InMemoryAuthors();
            var articles = new InMemoryArticles();
            var teasers = new InMemoryTeasers();
            var payments = new InMemoryPayments();

            authorService = new AuthorService(authors);
            articleService = new ArticleService(authors, articles, payments);
            publishingService = new PublishingService(authors, articles, teasers, payments);
            teaserService = new TeaserService(teasers);
            paymentService = new PaymentService(payments);
        }

        [Fact]
        public void SubmitArticle()
        {
            authorService.Hire("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50);
            articleService.Submit("article-1", "author-1", "headline");

            teaserService.Get("homepage").Should().BeEmpty();
            paymentService.GetByBankAccount("123-4567-89").Should().ContainInOrder(
                new PaymentDto(50, "123-4567-89", "Freddy Kruger", "headline")
            );
        }

        [Fact]
        public void PublishArticle()
        {
            authorService.Hire("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50);
            articleService.Submit("article-1", "author-1", "headline");
            publishingService.Publish("article-1");

            teaserService.Get("homepage").Should().ContainInOrder(
                new TeaserDto("headline", "Freddy Kruger")
            );
            paymentService.GetByBankAccount("123-4567-89").Should().ContainInOrder(
                new PaymentDto(50, "123-4567-89", "Freddy Kruger", "headline")
            );
        }
    }
}