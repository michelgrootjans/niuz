namespace niuz.application.entities
{
    public class Article
    {
        private readonly string articleId;

        public Article(string articleId, string authorId, string headline)
        {
            this.articleId = articleId;
            AuthorId = authorId;
            Headline = headline;
        }

        public string AuthorId { get; }
        public string Headline { get; }

        protected bool Equals(Article other)
        {
            return articleId == other.articleId;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Article) obj);
        }

        public override int GetHashCode()
        {
            return (articleId != null ? articleId.GetHashCode() : 0);
        }
    }
}