package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.events.ArticlePublished;
import com.dddeurope.niuz.events.EventPublisher;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;

public class PublishingService {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final PaymentRepository payments;
    private final EventPublisher publisher;

    public PublishingService(AuthorRepository authors, ArticleRepository articles, PaymentRepository payments, EventPublisher publisher) {
        this.authors = authors;
        this.articles = articles;
        this.payments = payments;
        this.publisher = publisher;
    }

    public void publish(String articleId) {
        Article article = articles.getByArticleId(articleId);
        Author author = authors.getByAuthorId(article.getAuthorId());

        publisher.publish(new ArticlePublished(article.getHeadline(), author.getName()));
        if (author.paysByPublication()) {
            payments.save(new Payment(author.getRate(), author.getBankAccount(), author.getName(), article.getHeadline()));
        }
    }
}
