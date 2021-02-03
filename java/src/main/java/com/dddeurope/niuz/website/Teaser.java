package com.dddeurope.niuz.website;

import java.util.Objects;

public class Teaser {
    private final String headline;
    private final String author;

    public Teaser(String headline, String author) {
        this.headline = headline;
        this.author = author;
    }

    public String getHeadline() {
        return headline;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Teaser{" +
                "headline='" + headline + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaser teaser = (Teaser) o;
        return Objects.equals(headline, teaser.headline) && Objects.equals(author, teaser.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, author);
    }
}
