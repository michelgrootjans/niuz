package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.commands.CommandDispatcher;
import com.dddeurope.niuz.finance.ContractRepository;
import com.dddeurope.niuz.finance.PaymentDto;
import com.dddeurope.niuz.finance.PaymentService;
import com.dddeurope.niuz.hr.AuthorService;
import com.dddeurope.niuz.hr.HireAuthor;
import com.dddeurope.niuz.newsroom.NewsroomService;
import com.dddeurope.niuz.website.TeaserDto;
import com.dddeurope.niuz.newsroom.ArticleRepository;
import com.dddeurope.niuz.newsroom.AuthorRepository;
import com.dddeurope.niuz.finance.PaymentRepository;
import com.dddeurope.niuz.website.TeaserRepository;
import com.dddeurope.niuz.website.TeaserService;

import java.util.List;

public class TestFacade {
    private final AuthorService authorService;
    private final NewsroomService newsroomService;
    private final TeaserService teaserService;
    private final PaymentService paymentService;

    public TestFacade() {
        AuthorRepository authors = new InMemoryAuthors();
        ArticleRepository articles = new InMemoryArticles();
        TeaserRepository teasers = new InMemoryTeasers();
        PaymentRepository payments = new InMemoryPayments();
        ContractRepository contracts = new InMemoryContractRepository();
        InMemoryEventBus eventBus = new InMemoryEventBus();

        authorService = new AuthorService(eventBus);
        newsroomService = new NewsroomService(authors, articles, eventBus, eventBus);
        teaserService = new TeaserService(teasers, eventBus);
        paymentService = new PaymentService(contracts, payments, eventBus);
    }

    public void hire(String authorId, String authorName, String bankAccount, String contractType, int rate) {
        new CommandDispatcher(authorService).dispatch(new HireAuthor(authorId, authorName, contractType, rate, bankAccount));
    }

    public void submit(String articleId, String authorId, String headline) {
        newsroomService.submit(articleId, authorId, headline);
    }

    public void publish(String articleId) {
        newsroomService.publish(articleId);
    }

    public List<TeaserDto> get(String page) {
        return teaserService.get(page);
    }

    public List<PaymentDto> getByBankAccount(String bankAccount) {
        return paymentService.getByBankAccount(bankAccount);
    }
}
