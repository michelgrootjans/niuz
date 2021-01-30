package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.entities.Teaser;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;
import com.dddeurope.niuz.repositories.TeaserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PublishingServiceTest {
    private AuthorRepository authors;
    private ArticleRepository articles;
    private TeaserRepository teasers;
    private PublishingService publishingService;
    private PaymentRepository payments;

    @BeforeEach
    void setUp() {
        authors = mock(AuthorRepository.class);
        articles = mock(ArticleRepository.class);
        teasers = mock(TeaserRepository.class);
        payments = mock(PaymentRepository.class);
        publishingService = new PublishingService(authors, articles, teasers, payments);
    }

    @Test
    void submitArticleWithoutPay() {
        when(authors.getByAuthorId("author-1")).thenReturn(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50));
        when(articles.getByArticleId("article-1")).thenReturn(new Article("article-1", "author-1", "headline"));

        publishingService.publish("article-1");

        verify(teasers).save("homepage", new Teaser("headline", "Freddy Kruger"));
        verify(payments, never()).save(any());
    }

    @Test
    void submitArticleAndPay() {
        when(authors.getByAuthorId("author-1")).thenReturn(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-publication", 100));
        when(articles.getByArticleId("article-1")).thenReturn(new Article("article-1", "author-1", "headline"));

        publishingService.publish("article-1");

        verify(teasers).save("homepage", new Teaser("headline", "Freddy Kruger"));
        verify(payments).save(new Payment(100, "123-4567-89", "Freddy Kruger", "headline"));
    }

}
