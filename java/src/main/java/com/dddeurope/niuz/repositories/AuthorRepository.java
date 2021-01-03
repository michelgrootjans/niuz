package com.dddeurope.niuz.repositories;

import com.dddeurope.niuz.entities.Author;

public interface AuthorRepository {
    void save(Author author);
    Author getByAuthorId(String authorId);
}
