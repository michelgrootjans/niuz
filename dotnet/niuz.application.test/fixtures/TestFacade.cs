using System.Collections.Generic;
using niuz.application.finance;
using niuz.application.services;
using niuz.application.website;

namespace niuz.application.fixtures
{
    public class TestFacade
    {
        private readonly AuthorService authorService;
        private readonly ArticleService articleService;
        private readonly PublishingService publishingService;
        private readonly TeaserService teaserService;
        private readonly PaymentService paymentService;

        public TestFacade()
        {
            var authors = new InMemoryAuthors();
            var articles = new InMemoryArticles();
            var teasers = new InMemoryTeasers();
            var payments = new InMemoryPayments();
            var eventBus = new InMemoryEventBus();

            authorService = new AuthorService(authors, eventBus);
            articleService = new ArticleService(articles, eventBus);
            publishingService = new PublishingService(authors, articles, eventBus, eventBus);
            teaserService = new TeaserService(teasers, eventBus);
            paymentService = new PaymentService(new InMemoryContracts(), payments, eventBus);
        }

        public void Hire(string authorId, string authorName, string bankAccount, string contractType, int rate)
        {
            authorService.Hire(authorId, authorName, bankAccount, contractType, rate);
        }

        public void Submit(string articleId, string authorId, string headline)
        {
            articleService.Submit(articleId, authorId, headline);
        }

        public void Publish(string articleId)
        {
            publishingService.Publish(articleId);
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