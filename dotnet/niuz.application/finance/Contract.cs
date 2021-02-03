namespace niuz.application.finance
{
    public class Contract
    {
        private readonly string authorId;
        private readonly int rate;
        private readonly string bankAccount;
        private readonly string authorName;

        public Contract(string authorId, string contractType, int rate, string bankAccount, string authorName)
        {
            this.authorId = authorId;
            this.PaysBySubmission = contractType == "pay-by-submission";
            this.PaysByPublication = contractType == "pay-by-publication";

            this.rate = rate;
            this.bankAccount = bankAccount;
            this.authorName = authorName;
        }

        public bool PaysByPublication { get; }
        public bool PaysBySubmission { get; }

        public bool IsOwnedBy(string authorId)
        {
            return this.authorId == authorId;
        }

        public Payment GeneratePayment(string description)
        {
            return new Payment(rate, bankAccount, authorName, description);
        }
    }
}