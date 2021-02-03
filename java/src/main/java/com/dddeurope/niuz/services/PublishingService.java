package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.events.ArticlePublished;
import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.EventPublisher;
import com.dddeurope.niuz.events.Topic;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;

public class PublishingService {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final EventPublisher publisher;

    public PublishingService(AuthorRepository authors, ArticleRepository articles, Topic topic, EventPublisher publisher) {
        this.authors = authors;
        this.articles = articles;
        this.publisher = publisher;
        topic.subscribe(ContractSigned.class, this::hire);
    }

    private void hire(ContractSigned event) {
        authors.save(new Author(event.getAuthorId(), event.getAuthorName(), null, null, 0));
    }

    public void publish(String articleId) {
        Article article = articles.getByArticleId(articleId);
        Author author = authors.getByAuthorId(article.getAuthorId());

        publisher.publish(new ArticlePublished(author.getId(), author.getName(), article.getHeadline()));
    }
}
