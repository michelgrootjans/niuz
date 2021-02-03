package com.dddeurope.niuz.events;

public class ArticleSubmitted {
    private final String authorId;
    private final String headline;

    public ArticleSubmitted(String authorId, String headline) {
        this.authorId = authorId;
        this.headline = headline;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getHeadline() {
        return headline;
    }
}
