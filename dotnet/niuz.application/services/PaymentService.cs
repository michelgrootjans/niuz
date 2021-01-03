using System.Collections.Generic;
using System.Linq;
using niuz.application.dtos;
using niuz.application.entities;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class PaymentService
    {
        private readonly IPaymentRepository payments;

        public PaymentService(IPaymentRepository payments)
        {
            this.payments = payments;
        }

        public IEnumerable<PaymentDto> GetByBankAccount(string bankAccount)
        {
            return payments.GetByBankAccount(bankAccount)
                .Select(Map);
        }

        private PaymentDto Map(Payment payment)
        {
            return new PaymentDto(payment.Amount, payment.BankAccount, payment.Recipient, payment.Description);
        }
    }
}