namespace niuz.application.events
{
    public class ArticlePublished
    {
        public ArticlePublished(string authorId, string authorName, string headline)
        {
            Headline = headline;
            AuthorName = authorName;
            AuthorId = authorId;
        }

        public string AuthorId { get; }
        public string AuthorName { get; }
        public string Headline { get; }
    }
}