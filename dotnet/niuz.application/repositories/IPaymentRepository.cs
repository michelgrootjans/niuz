using System.Collections.Generic;
using niuz.application.entities;

namespace niuz.application.repositories
{
    public interface IPaymentRepository
    {
        void Save(Payment payment);
        List<Payment> GetByBankAccount(string bankAccount);
    }
}