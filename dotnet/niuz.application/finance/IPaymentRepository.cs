using System.Collections.Generic;

namespace niuz.application.finance
{
    public interface IPaymentRepository
    {
        void Save(Payment payment);
        IEnumerable<Payment> GetByBankAccount(string bankAccount);
    }
}