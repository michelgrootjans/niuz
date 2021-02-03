package com.dddeurope.niuz.services;


import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.EventPublisher;
import com.dddeurope.niuz.repositories.AuthorRepository;

public class AuthorService {
    private final AuthorRepository authors;
    private final EventPublisher publisher;

    public AuthorService(AuthorRepository authors, EventPublisher publisher) {
        this.authors = authors;
        this.publisher = publisher;
    }

    public void hire(String authorId, String authorName, String bankAccount, String contractType, int rate) {
        publisher.publish(new ContractSigned(authorId, contractType, rate, bankAccount, authorName));
        authors.save(new Author(authorId, authorName, bankAccount, contractType, rate));
    }
}
