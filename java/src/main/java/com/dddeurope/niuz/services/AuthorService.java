package com.dddeurope.niuz.services;


import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.repositories.AuthorRepository;

public class AuthorService {
    private final AuthorRepository authors;

    public AuthorService(AuthorRepository authors) {
        this.authors = authors;
    }

    public void hire(String authorId, String authorName, String bankAccount, String contractType) {
        authors.save(new Author(authorId, authorName, bankAccount, contractType));
    }
}
