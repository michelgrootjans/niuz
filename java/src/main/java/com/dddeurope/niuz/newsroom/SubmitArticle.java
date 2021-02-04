package com.dddeurope.niuz.newsroom;

public class SubmitArticle {
    private final String articleId;
    private final String authorId;
    private final String headline;

    public SubmitArticle(String articleId, String authorId, String headline) {
        this.articleId = articleId;
        this.authorId = authorId;
        this.headline = headline;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getHeadline() {
        return headline;
    }
}
