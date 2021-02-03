namespace niuz.application.events
{
    public class ArticleSubmitted
    {
        public ArticleSubmitted(string authorId, string headline)
        {
            AuthorId = authorId;
            Headline = headline;
        }

        public string AuthorId { get; }
        public string Headline { get; }
    }
}