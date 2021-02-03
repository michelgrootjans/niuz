using System.Collections.Generic;
using System.Linq;
using niuz.application.events;

namespace niuz.application.finance
{
    public class PaymentService
    {
        private readonly IPaymentRepository payments;
        private readonly IContractRepository contracts;

        public PaymentService(IContractRepository contracts, IPaymentRepository payments, ITopic topic)
        {
            this.payments = payments;
            this.contracts = contracts;
            
            topic.Subscribe<ContractSigned>(CreateContract);
            topic.Subscribe<ArticleSubmitted>(Pay);
            topic.Subscribe<ArticlePublished>(Pay);
        }

        private void CreateContract(ContractSigned @event)
        {
            contracts.Add(new Contract(@event.AuthorId, @event.ContractType, @event.Rate, @event.BankAccount, @event.AuthorName));
        }

        private void Pay(ArticleSubmitted @event)
        {
            foreach (var contract in contracts.OwnedBy(@event.AuthorId))
            {
                if(contract.PaysBySubmission) payments.Save(contract.GeneratePayment(@event.Headline));
            }
        }

        private void Pay(ArticlePublished @event)
        {
            foreach (var contract in contracts.OwnedBy(@event.AuthorId))
            {
                if(contract.PaysByPublication) payments.Save(contract.GeneratePayment(@event.Headline));
            }
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