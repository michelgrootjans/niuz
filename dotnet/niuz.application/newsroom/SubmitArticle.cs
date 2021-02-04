namespace niuz.application.newsroom
{
    public class SubmitArticle
    {
        public string ArticleId { get; }
        public string AuthorId { get; }
        public string Headline { get; }

        public SubmitArticle(string articleId, string authorId, string headline)
        {
            ArticleId = articleId;
            AuthorId = authorId;
            Headline = headline;
        }
    }
}