package com.dddeurope.niuz.website;

import java.util.Objects;

public class TeaserDto {
    private final String headline;
    private final String author;

    public TeaserDto(String headline, String author) {
        this.headline = headline;
        this.author = author;
    }

    @Override
    public String toString() {
        return "TeaserDto{" +
                "headline='" + headline + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaserDto teaserDto = (TeaserDto) o;
        return Objects.equals(headline, teaserDto.headline) && Objects.equals(author, teaserDto.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, author);
    }
}
