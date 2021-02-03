package com.dddeurope.niuz.events;

public class ArticlePublished {
    private final String authorId;
    private final String author;
    private final String headline;

    public ArticlePublished(String authorId, String author, String headline) {
        this.headline = headline;
        this.author = author;
        this.authorId = authorId;
    }

    public String getHeadline() {
        return headline;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorId() {
        return authorId;
    }
}
