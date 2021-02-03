using System.Collections.Generic;
using System.Linq;
using niuz.application.entities;
using niuz.application.finance;
using niuz.application.repositories;

namespace niuz.application.fixtures
{
    public class InMemoryPayments : IPaymentRepository
    {
        readonly ICollection<Payment> payments = new List<Payment>();
        
        public void Save(Payment payment)
        {
            payments.Add(payment);
        }

        public IEnumerable<Payment> GetByBankAccount(string bankAccount)
        {
            return payments.Where(payment => payment.BankAccount == bankAccount);
        }
    }}