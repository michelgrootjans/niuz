namespace niuz.application.events
{
    public class ContractSigned
    {
        public ContractSigned(string authorId, string authorName, string contractType, int rate, string bankAccount)
        {
            AuthorId = authorId;
            AuthorName = authorName;
            ContractType = contractType;
            Rate = rate;
            BankAccount = bankAccount;
        }

        public string AuthorId { get; }
        public string AuthorName { get; }
        public string ContractType { get; }
        public int Rate { get; }
        public string BankAccount { get; }
    }
}