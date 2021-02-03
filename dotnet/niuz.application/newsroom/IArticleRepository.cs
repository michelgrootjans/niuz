namespace niuz.application.newsroom
{
    public interface IArticleRepository
    {
        void Save(Article article);
        Article GetByArticleId(string articleId);
    }
}