using niuz.application.entities;
using niuz.application.repositories;
using NSubstitute;
using Xunit;

namespace niuz.application.services
{
  public class PublishingServiceShould
  {
    [Fact]
    public void PayTheAuthorWhenAnArticleHasBeenPublished()
    {
      var testContext = new TestContextBuilder();

      testContext.authors.GetByAuthorId("author-1").Returns(new Author("author-1", "Freddy Kruger", "123-4567-89"));
      testContext.articles.GetByArticleId("article-1").Returns(new Article("article-1", "author-1", "headline"));

      var publishingService = testContext.Get<PublishingService>();

      publishingService.PublishArticleById("article-1");

      testContext.teasers.Received().Save("homepage", new Teaser("headline", "Freddy Kruger"));
      testContext.payments.Received().Save(new Payment(100, "123-4567-89", "Freddy Kruger", "headline"));
    }
  }
}
