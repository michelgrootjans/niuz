namespace niuz.application.entities
{
    public class Author
    {
        private readonly string id;

        public Author(string id, string name, string bankAccount)
        {
            this.id = id;
            Name = name;
            BankAccount = bankAccount;
        }

        public string Name { get; }
        public string BankAccount { get; }

        protected bool Equals(Author other)
        {
            return id == other.id;
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
            return (id != null ? id.GetHashCode() : 0);
        }
    }
}