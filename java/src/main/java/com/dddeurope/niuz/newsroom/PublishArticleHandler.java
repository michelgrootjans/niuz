package com.dddeurope.niuz.newsroom;

import com.dddeurope.niuz.commands.CommandHandler;
import com.dddeurope.niuz.events.*;

public class PublishArticleHandler implements CommandHandler<PublishArticle> {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final EventPublisher publisher;

    public PublishArticleHandler(AuthorRepository authors, ArticleRepository articles, EventPublisher publisher) {
        this.authors = authors;
        this.articles = articles;
        this.publisher = publisher;
    }

    @Override
    public boolean canHandle(Object command) {
        return command instanceof PublishArticle;
    }

    @Override
    public void handle(PublishArticle command) {
        Article article = articles.getByArticleId(command.getArticleId());
        Author author = authors.getByAuthorId(article.getAuthorId());

        publisher.publish(new ArticlePublished(author.getId(), author.getName(), article.getHeadline()));
    }

}
