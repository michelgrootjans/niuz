using System;

namespace niuz.application.dtos
{
    public class PaymentDto
    {
        public int Amount { get; }
        public string BankAccount { get; }
        public string Recipient { get; }
        public string Description { get; }

        public PaymentDto(int amount, string bankAccount, string recipient, string description)
        {
            Amount = amount;
            BankAccount = bankAccount;
            Recipient = recipient;
            Description = description;
        }

        protected bool Equals(PaymentDto other)
        {
            return Amount == other.Amount && BankAccount == other.BankAccount && Recipient == other.Recipient && Description == other.Description;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((PaymentDto) obj);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Amount, BankAccount, Recipient, Description);
        }
    }
}