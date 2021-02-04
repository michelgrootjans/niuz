using FluentAssertions;
using niuz.application.finance;
using niuz.application.fixtures;
using niuz.application.hr;
using niuz.application.newsroom;
using niuz.application.website;
using Xunit;

namespace niuz.application.scenarios
{
    public class PayByPublication
    {
        private readonly TestFacade app;

        public PayByPublication()
        {
            app = new TestFacade();
        }

        [Fact]
        public void SubmitArticle()
        {
            app.Dispatch(new HireAuthor("author-1", "Freddy Kruger", "pay-by-publication", 100, "123-4567-89"));
            app.Dispatch(new SubmitArticle("article-1", "author-1", "headline"));

            app.Get("homepage").Should().BeEmpty();
            app.GetByBankAccount("123-4567-89").Should().BeEmpty();
        }

        [Fact]
        public void PublishArticle()
        {
            app.Dispatch(new HireAuthor("author-1", "Freddy Kruger", "pay-by-publication", 100, "123-4567-89"));
            app.Dispatch(new SubmitArticle("article-1", "author-1", "headline"));
            app.Publish("article-1");

            app.Get("homepage").Should().ContainInOrder(
                new TeaserDto("headline", "Freddy Kruger")
            );
            app.GetByBankAccount("123-4567-89").Should().ContainInOrder(
                new PaymentDto(100, "123-4567-89", "Freddy Kruger", "headline")
            );
        }
    }
}