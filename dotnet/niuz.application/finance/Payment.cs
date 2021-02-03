using System;

namespace niuz.application.finance
{
    public class Payment
    {
        public int Amount { get; }

        public string BankAccount { get; }

        public string Recipient { get; }

        public string Description { get; }

        public Payment(int amount, string bankAccount, string recipient, string description)
        {
            Amount = amount;
            BankAccount = bankAccount;
            Recipient = recipient;
            Description = description;
        }

        protected bool Equals(Payment other)
        {
            return Amount == other.Amount && BankAccount == other.BankAccount && Recipient == other.Recipient && Description == other.Description;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Payment) obj);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Amount, BankAccount, Recipient, Description);
        }
    }
}