namespace niuz.application.newsroom
{
    public class Author
    {
        public string Id { get; }
        public string Name { get; }
        public string BankAccount { get; }
        private string ContractType { get; }
        public int Rate { get; }
        public bool PaysBySubmission => ContractType == "pay-by-submission";
        public bool PaysByPublication => ContractType == "pay-by-publication";


        public Author(string id, string name, string bankAccount, string contractType, int rate)
        {
            Id = id;
            Name = name;
            BankAccount = bankAccount;
            ContractType = contractType;
            Rate = rate;
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