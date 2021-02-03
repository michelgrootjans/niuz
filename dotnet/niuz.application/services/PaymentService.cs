using System.Collections.Generic;
using System.Linq;
using niuz.application.dtos;
using niuz.application.entities;
using niuz.application.events;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class PaymentService
    {
        private readonly IPaymentRepository payments;
        private List<Contract> contracts;

        public PaymentService(IPaymentRepository payments, ITopic topic)
        {
            this.payments = payments;
            this.contracts = new List<Contract>();
            
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
            foreach (var contract in ContractsFor(@event.AuthorId))
            {
                if(contract.PaysBySubmission) payments.Save(contract.GeneratePayment(@event.Headline));
            }
        }

        private void Pay(ArticlePublished @event)
        {
            foreach (var contract in ContractsFor(@event.AuthorId))
            {
                if(contract.PaysByPublication) payments.Save(contract.GeneratePayment(@event.Headline));
            }
        }
        
        private IEnumerable<Contract> ContractsFor(string authorId)
        {
            return contracts
                .Where(c => c.IsOwnedBy(authorId))
                .Select(c => c);
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
        
        internal class Contract
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
}