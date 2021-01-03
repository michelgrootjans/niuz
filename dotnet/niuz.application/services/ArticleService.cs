using niuz.application.entities;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class ArticleService
    {
        private readonly IArticleRepository articles;

        public ArticleService(IArticleRepository articles)
        {
            this.articles = articles;
        }

        public void Save(string articleId, string authorId, string headline)
        {
            articles.Save(new Article(articleId, authorId, headline));
        }
    }
}