namespace niuz.application.newsroom
{
    public class PublishArticle
    {
        public string ArticleId { get; }

        public PublishArticle(string articleId)
        {
            ArticleId = articleId;
        }
    }
}