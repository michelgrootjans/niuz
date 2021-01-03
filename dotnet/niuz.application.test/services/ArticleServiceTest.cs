using niuz.application.entities;
using niuz.application.repositories;
using NSubstitute;
using Xunit;

namespace niuz.application.services
{
    public class ArticleServiceTest
    {
        private readonly IArticleRepository articles;
        private readonly ArticleService articleService;

        public ArticleServiceTest()
        {
            articles = Substitute.For<IArticleRepository>();
            articleService = new ArticleService(articles);
        }

        [Fact]
        public void SubmitArticle()
        {
            articleService.Save("article-1", "author-1", "headline");
            articles.Received().Save(new Article("article-1", "author-1", "headline"));
        }
    }
}