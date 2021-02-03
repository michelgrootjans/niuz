using System;

namespace niuz.application.website
{
    public class TeaserDto
    {
        private readonly string headline;
        private readonly string author;

        public TeaserDto(string headline, string author)
        {
            this.headline = headline;
            this.author = author;
        }

        protected bool Equals(TeaserDto other)
        {
            return headline == other.headline && author == other.author;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((TeaserDto) obj);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(headline, author);
        }
    }
}