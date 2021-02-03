using System;

namespace niuz.application.website
{
    public class Teaser
    {
        public string Headline { get; }

        public string Author { get; }

        public Teaser(string headline, string author)
        {
            Headline = headline;
            Author = author;
        }

        protected bool Equals(Teaser other)
        {
            return Headline == other.Headline && Author == other.Author;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Teaser) obj);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Headline, Author);
        }
    }
}