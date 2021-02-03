package com.dddeurope.niuz.events;

public class ArticlePublished {
    private String headline;
    private String author;

    public ArticlePublished(String headline, String author) {
        this.headline = headline;
        this.author = author;
    }

    public String getHeadline() {
        return headline;
    }

    public String getAuthor() {
        return author;
    }
}
