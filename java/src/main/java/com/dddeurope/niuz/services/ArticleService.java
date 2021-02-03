package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.events.ArticleSubmitted;
import com.dddeurope.niuz.events.EventPublisher;
import com.dddeurope.niuz.repositories.ArticleRepository;

public class ArticleService {
    private final ArticleRepository articles;
    private final EventPublisher publisher;

    public ArticleService(ArticleRepository articles, EventPublisher publisher) {
        this.articles = articles;
        this.publisher = publisher;
    }

    public void submit(String articleId, String authorId, String headline) {
        articles.save(new Article(articleId, authorId, headline));
        publisher.publish(new ArticleSubmitted(authorId, headline));
    }
}
