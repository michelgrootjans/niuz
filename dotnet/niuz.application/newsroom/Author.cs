namespace niuz.application.newsroom
{
    public class Author
    {
        public string Id { get; }
        public string Name { get; }


        public Author(string id, string name)
        {
            Id = id;
            Name = name;
        }

        protected bool Equals(Author other)
        {
            return Id == other.Id;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Author) obj);
        }

        public override int GetHashCode()
        {
            return (Id != null ? Id.GetHashCode() : 0);
        }
    }
}