using System.Collections.Generic;
using FluentAssertions;
using niuz.application.dtos;
using niuz.application.entities;
using niuz.application.repositories;
using NSubstitute;
using Xunit;

namespace niuz.application.services
{
    public class PaymentServiceTest
    {
        private readonly IPaymentRepository payments;
        private readonly PaymentService paymentService;

        public PaymentServiceTest()
        {
            payments = Substitute.For<IPaymentRepository>();
            paymentService = new PaymentService(payments);
        }
        
        [Fact]
        public void GetPayments()
        {
            payments.GetByBankAccount("123-4567-89").Returns(new List<Payment> {new Payment(100, "123-4567-89", "Freddy Kruger", "payment for: headline")});
            paymentService.GetByBankAccount("123-4567-89").Should().ContainInOrder(
                new PaymentDto(100, "123-4567-89", "Freddy Kruger", "payment for: headline")
            );
        }
    }
}