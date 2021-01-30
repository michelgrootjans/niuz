package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.entities.Teaser;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;
import com.dddeurope.niuz.repositories.TeaserRepository;

public class PublishingService {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final TeaserRepository teasers;
    private final PaymentRepository payments;

    public PublishingService(AuthorRepository authors, ArticleRepository articles, TeaserRepository teasers, PaymentRepository payments) {
        this.authors = authors;
        this.articles = articles;
        this.teasers = teasers;
        this.payments = payments;
    }

    public void publish(String articleId) {
        Article article = articles.getByArticleId(articleId);
        Author author = authors.getByAuthorId(article.getAuthorId());

        teasers.save("homepage", new Teaser(article.getHeadline(), author.getName()));
        if (author.paysByPublication()) {
            payments.save(new Payment(author.getRate(), author.getBankAccount(), author.getName(), article.getHeadline()));
        }
    }
}
