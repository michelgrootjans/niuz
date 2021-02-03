package com.dddeurope.niuz.newsroom;

import java.util.Objects;

public class Article {
    private final String id;
    private final String authorId;
    private final String headline;

    public Article(String id, String authorId, String headline) {
        this.id = id;
        this.authorId = authorId;
        this.headline = headline;
    }

    public String getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getHeadline() {
        return headline;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", headline='" + headline + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
