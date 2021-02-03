package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.events.ArticlePublished;
import com.dddeurope.niuz.events.EventPublisher;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;

public class PublishingService {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final EventPublisher publisher;

    public PublishingService(AuthorRepository authors, ArticleRepository articles, EventPublisher publisher) {
        this.authors = authors;
        this.articles = articles;
        this.publisher = publisher;
    }

    public void publish(String articleId) {
        Article article = articles.getByArticleId(articleId);
        Author author = authors.getByAuthorId(article.getAuthorId());

        publisher.publish(new ArticlePublished(author.getId(), author.getName(), article.getHeadline()));
    }
}
