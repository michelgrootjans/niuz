using System.Collections.Generic;
using niuz.application.commands;
using niuz.application.finance;
using niuz.application.hr;
using niuz.application.newsroom;
using niuz.application.website;

namespace niuz.application.fixtures
{
    public class TestFacade
    {
        private readonly TeaserService teaserService;
        private readonly PaymentService paymentService;
        private readonly CommandDispatcher commandDispatcher;

        public TestFacade()
        {
            var eventBus = new InMemoryEventBus();

            commandDispatcher = new CommandDispatcher(
                new AuthorService(eventBus),
                new NewsRoomService(new InMemoryAuthors(), new InMemoryArticles(), eventBus, eventBus)
            );

            paymentService = new PaymentService(new InMemoryContracts(), new InMemoryPayments(), eventBus);
            teaserService = new TeaserService(new InMemoryTeasers(), eventBus);
        }

        public void Dispatch<T>(T command)
        {
            commandDispatcher.Dispatch(command);
        }

        public IEnumerable<TeaserDto> Get(string page)
        {
            return teaserService.Get(page);
        }

        public IEnumerable<PaymentDto> GetByBankAccount(string bankAccount)
        {
            return paymentService.GetByBankAccount(bankAccount);
        }
    }
}