package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.commands.CommandDispatcher;
import com.dddeurope.niuz.finance.ContractRepository;
import com.dddeurope.niuz.finance.PaymentDto;
import com.dddeurope.niuz.finance.PaymentService;
import com.dddeurope.niuz.hr.AuthorService;
import com.dddeurope.niuz.newsroom.*;
import com.dddeurope.niuz.website.TeaserDto;
import com.dddeurope.niuz.finance.PaymentRepository;
import com.dddeurope.niuz.website.TeaserRepository;
import com.dddeurope.niuz.website.TeaserService;

import java.util.List;

public class TestFacade {
    private final TeaserService teaserService;
    private final PaymentService paymentService;
    private final CommandDispatcher commandDispatcher;

    public TestFacade() {
        AuthorRepository authors = new InMemoryAuthors();
        ArticleRepository articles = new InMemoryArticles();
        TeaserRepository teasers = new InMemoryTeasers();
        PaymentRepository payments = new InMemoryPayments();
        ContractRepository contracts = new InMemoryContractRepository();
        InMemoryEventBus eventBus = new InMemoryEventBus();

        commandDispatcher = new CommandDispatcher(
                new AuthorService(eventBus),
                new SubmitArticleHandler(articles, eventBus),
                new PublishArticleHandler(authors, articles, eventBus)
        );
        new AuthorOnboarding(authors, eventBus);
        teaserService = new TeaserService(teasers, eventBus);
        paymentService = new PaymentService(contracts, payments, eventBus);
    }

    public void dispatch(Object command) {
        commandDispatcher.dispatch(command);
    }

    public List<TeaserDto> get(String page) {
        return teaserService.get(page);
    }

    public List<PaymentDto> getByBankAccount(String bankAccount) {
        return paymentService.getByBankAccount(bankAccount);
    }
}
