package com.dddeurope.niuz.newsroom;

public class PublishArticle {
    private final String articleId;

    public PublishArticle(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }
}
