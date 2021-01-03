using niuz.application.entities;

namespace niuz.application.repositories
{
    public interface IArticleRepository
    {
        void Save(Article article);
        Article GetByArticleId(string articleId);
    }
}