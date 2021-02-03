package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.newsroom.Author;
import com.dddeurope.niuz.newsroom.AuthorRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAuthors implements AuthorRepository {
    final Map<String, Author> authors = new HashMap<>();

    @Override
    public void save(Author author) {
        authors.put(author.getId(), author);
    }

    @Override
    public Author getByAuthorId(String authorId) {
        return authors.get(authorId);
    }
}
