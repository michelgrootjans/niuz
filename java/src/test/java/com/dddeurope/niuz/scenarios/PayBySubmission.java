package com.dddeurope.niuz.scenarios;

import com.dddeurope.niuz.dtos.PaymentDto;
import com.dddeurope.niuz.dtos.TeaserDto;
import com.dddeurope.niuz.fixtures.InMemoryArticles;
import com.dddeurope.niuz.fixtures.InMemoryAuthors;
import com.dddeurope.niuz.fixtures.InMemoryPayments;
import com.dddeurope.niuz.fixtures.InMemoryTeasers;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;
import com.dddeurope.niuz.repositories.TeaserRepository;
import com.dddeurope.niuz.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PayBySubmission {
    private PublishingService publishingService;
    private AuthorService authorService;
    private ArticleService articleService;
    private TeaserService teaserService;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
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

    @Test
    void submitArticle() {
        authorService.hire("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50);
        articleService.submit("article-1", "author-1", "headline");

        assertThat(teaserService.get("homepage")).isEmpty();
        assertThat(paymentService.getByBankAccount("123-4567-89")).containsExactly(
                new PaymentDto(50, "123-4567-89", "Freddy Kruger", "headline")
        );
    }

    @Test
    void publishArticle() {
        authorService.hire("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50);
        articleService.submit("article-1", "author-1", "headline");
        publishingService.publish("article-1");

        assertThat(teaserService.get("homepage")).containsExactly(
                new TeaserDto("headline", "Freddy Kruger")
        );
        assertThat(paymentService.getByBankAccount("123-4567-89")).containsExactly(
                new PaymentDto(50, "123-4567-89", "Freddy Kruger", "headline")
        );
    }
}
