using System.Collections.Generic;
using niuz.application.entities;
using niuz.application.repositories;

namespace niuz.application.fixtures
{
    public class InMemoryArticles : IArticleRepository
    {
        private readonly Dictionary<string, Article> articles = new Dictionary<string, Article>();

        public void Save(Article article)
        {
            articles[article.Id] = article;
        }

        public Article GetByArticleId(string articleId)
        {
            return articles[articleId];
        }
    }
}