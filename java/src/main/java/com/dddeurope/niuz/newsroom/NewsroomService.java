package com.dddeurope.niuz.newsroom;

import com.dddeurope.niuz.commands.CommandHandler;
import com.dddeurope.niuz.events.*;

public class NewsroomService implements CommandHandler<SubmitArticle> {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final EventPublisher publisher;

    public NewsroomService(AuthorRepository authors, ArticleRepository articles, Topic topic, EventPublisher publisher) {
        this.authors = authors;
        this.articles = articles;
        this.publisher = publisher;
        topic.subscribe(ContractSigned.class, this::onboard);
    }

    private void onboard(ContractSigned event) {
        authors.save(new Author(event.getAuthorId(), event.getAuthorName()));
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

    public void publish(String articleId) {
        Article article = articles.getByArticleId(articleId);
        Author author = authors.getByAuthorId(article.getAuthorId());

        publisher.publish(new ArticlePublished(author.getId(), author.getName(), article.getHeadline()));
    }
}
