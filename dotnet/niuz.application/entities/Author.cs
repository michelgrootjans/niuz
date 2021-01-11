namespace niuz.application.entities
{
    public class Author
    {
        private readonly string id;
        public string Name { get; }
        public string BankAccount { get; }
        public string ContractType { get; }
        public bool PaysBySubmission => ContractType == "pay-by-submission";
        public bool PaysByPublication => ContractType == "pay-by-publication";

        public Author(string id, string name, string bankAccount, string contractType)
        {
            this.id = id;
            Name = name;
            BankAccount = bankAccount;
            ContractType = contractType;
        }

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