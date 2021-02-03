package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.events.ArticleSubmitted;
import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.EventPublisher;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;

public class ArticleService {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final PaymentRepository payments;
    private final EventPublisher publisher;

    public ArticleService(AuthorRepository authors, ArticleRepository articles, PaymentRepository payments, EventPublisher publisher) {
        this.authors = authors;
        this.articles = articles;
        this.payments = payments;
        this.publisher = publisher;
    }

    public void submit(String articleId, String authorId, String headline) {
        articles.save(new Article(articleId, authorId, headline));
        publisher.publish(new ArticleSubmitted(authorId, headline));
    }
}
