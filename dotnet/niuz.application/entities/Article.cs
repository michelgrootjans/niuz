namespace niuz.application.entities
{
    public class Article
    {
        public Article(string id, string authorId, string headline)
        {
            Id = id;
            AuthorId = authorId;
            Headline = headline;
        }

        public string Id { get; }
        public string AuthorId { get; }
        public string Headline { get; }

        protected bool Equals(Article other)
        {
            return Id == other.Id;
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
            return (Id != null ? Id.GetHashCode() : 0);
        }
    }
}