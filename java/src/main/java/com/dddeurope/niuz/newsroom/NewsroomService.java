package com.dddeurope.niuz.newsroom;

import com.dddeurope.niuz.events.*;

public class NewsroomService {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final EventPublisher publisher;

    public NewsroomService(AuthorRepository authors, ArticleRepository articles, Topic topic, EventPublisher publisher) {
        this.authors = authors;
        this.articles = articles;
        this.publisher = publisher;
        topic.subscribe(ContractSigned.class, this::hire);
    }

    private void hire(ContractSigned event) {
        authors.save(new Author(event.getAuthorId(), event.getAuthorName(), null, null, 0));
    }

    public void submit(String articleId, String authorId, String headline) {
        articles.save(new Article(articleId, authorId, headline));
        publisher.publish(new ArticleSubmitted(authorId, headline));
    }

    public void publish(String articleId) {
        Article article = articles.getByArticleId(articleId);
        Author author = authors.getByAuthorId(article.getAuthorId());

        publisher.publish(new ArticlePublished(author.getId(), author.getName(), article.getHeadline()));
    }
}
