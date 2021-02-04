package com.dddeurope.niuz.newsroom;

import com.dddeurope.niuz.commands.CommandHandler;
import com.dddeurope.niuz.events.*;

public class SubmitArticleHandler implements CommandHandler<SubmitArticle> {
    private final ArticleRepository articles;
    private final EventPublisher publisher;

    public SubmitArticleHandler(ArticleRepository articles, EventPublisher publisher) {
        this.articles = articles;
        this.publisher = publisher;
    }

    @Override
    public boolean canHandle(Object command) {
        return command instanceof SubmitArticle;
    }

    @Override
    public void handle(SubmitArticle command) {
        articles.save(new Article(command.getArticleId(), command.getAuthorId(), command.getHeadline()));
        publisher.publish(new ArticleSubmitted(command.getAuthorId(), command.getHeadline()));
    }
}
