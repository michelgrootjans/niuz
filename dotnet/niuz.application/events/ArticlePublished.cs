namespace niuz.application.events
{
    public class ArticlePublished
    {
        public ArticlePublished(string headline, string author)
        {
            Headline = headline;
            Author = author;
        }

        public string Headline { get; }
        public string Author { get; }
    }
}