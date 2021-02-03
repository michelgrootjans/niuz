package com.dddeurope.niuz.newsroom;

public interface AuthorRepository {
    void save(Author author);
    Author getByAuthorId(String authorId);
}
