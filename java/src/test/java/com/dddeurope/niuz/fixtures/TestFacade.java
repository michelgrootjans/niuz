package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.dtos.PaymentDto;
import com.dddeurope.niuz.website.TeaserDto;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;
import com.dddeurope.niuz.website.TeaserRepository;
import com.dddeurope.niuz.services.*;
import com.dddeurope.niuz.website.TeaserService;

import java.util.List;

public class TestFacade {
    private final AuthorService authorService;
    private final ArticleService articleService;
    private final PublishingService publishingService;
    private final TeaserService teaserService;
    private final PaymentService paymentService;

    public TestFacade() {
        AuthorRepository authors = new InMemoryAuthors();
        ArticleRepository articles = new InMemoryArticles();
        TeaserRepository teasers = new InMemoryTeasers();
        PaymentRepository payments = new InMemoryPayments();
        InMemoryEventBus eventBus = new InMemoryEventBus();

        authorService = new AuthorService(authors, eventBus);
        articleService = new ArticleService(articles, eventBus);
        publishingService = new PublishingService(authors, articles, payments, eventBus);
        teaserService = new TeaserService(teasers, eventBus);
        paymentService = new PaymentService(payments, eventBus);
    }

    public void hire(String authorId, String authorName, String bankAccount, String contractType, int rate) {
        authorService.hire(authorId, authorName, bankAccount, contractType, rate);
    }

    public void submit(String articleId, String authorId, String headline) {
        articleService.submit(articleId, authorId, headline);
    }

    public void publish(String articleId) {
        publishingService.publish(articleId);
    }

    public List<TeaserDto> get(String page) {
        return teaserService.get(page);
    }

    public List<PaymentDto> getByBankAccount(String bankAccount) {
        return paymentService.getByBankAccount(bankAccount);
    }
}
