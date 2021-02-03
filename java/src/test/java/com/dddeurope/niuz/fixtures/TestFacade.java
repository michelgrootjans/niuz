package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.dtos.PaymentDto;
import com.dddeurope.niuz.dtos.TeaserDto;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;
import com.dddeurope.niuz.repositories.TeaserRepository;
import com.dddeurope.niuz.services.*;

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

        authorService = new AuthorService(authors);
        articleService = new ArticleService(authors, articles, payments);
        publishingService = new PublishingService(authors, articles, teasers, payments);
        teaserService = new TeaserService(teasers);
        paymentService = new PaymentService(payments);
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
