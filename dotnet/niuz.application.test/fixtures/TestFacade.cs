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
        private readonly NewsRoomService newsRoomService;
        private readonly TeaserService teaserService;
        private readonly PaymentService paymentService;
        private readonly CommandDispatcher commandDispatcher;

        public TestFacade()
        {
            var authors = new InMemoryAuthors();
            var articles = new InMemoryArticles();
            var teasers = new InMemoryTeasers();
            var payments = new InMemoryPayments();
            var eventBus = new InMemoryEventBus();

            newsRoomService = new NewsRoomService(authors, articles, eventBus, eventBus);
            teaserService = new TeaserService(teasers, eventBus);
            paymentService = new PaymentService(new InMemoryContracts(), payments, eventBus);
            commandDispatcher = new CommandDispatcher(
                new AuthorService(eventBus),
                newsRoomService
                );
        }

        public void Dispatch<T>(T command)
        {
            commandDispatcher.Dispatch(command);
        }

        public void Publish(string articleId)
        {
            newsRoomService.Publish(articleId);
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