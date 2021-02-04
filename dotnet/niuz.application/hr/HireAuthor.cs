namespace niuz.application.hr
{
    public class HireAuthor
    {
        public string AuthorId { get; }
        public string AuthorName { get; }
        public string ContractType { get; }
        public int Rate { get; }
        public string BankAccount { get; }

        public HireAuthor(string authorId, string authorName, string contractType, int rate, string bankAccount)
        {
            AuthorId = authorId;
            AuthorName = authorName;
            ContractType = contractType;
            Rate = rate;
            BankAccount = bankAccount;
        }
    }
}