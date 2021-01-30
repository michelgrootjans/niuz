package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ArticleServiceTest {
    private AuthorRepository authors;
    private ArticleRepository articles;
    private ArticleService articleService;
    private PaymentRepository payments;

    @BeforeEach
    void setUp() {
        authors = mock(AuthorRepository.class);
        articles = mock(ArticleRepository.class);
        payments = mock(PaymentRepository.class);
        articleService = new ArticleService(authors, articles, payments);
    }

    @Test
    void submitArticleAndPay() {
        when(authors.getByAuthorId("author-1")).thenReturn(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 50));

        articleService.submit("article-1", "author-1", "headline");

        verify(articles).save(new Article("article-1", "author-1", "headline"));
        verify(payments).save(new Payment(50, "123-4567-89", "Freddy Kruger", "headline"));
    }

    @Test
    void submitArticleWithoutPay() {
        when(authors.getByAuthorId("author-1")).thenReturn(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-publication", 100));

        articleService.submit("article-1", "author-1", "headline");

        verify(articles).save(new Article("article-1", "author-1", "headline"));
        verify(payments, never()).save(any());
    }
}
