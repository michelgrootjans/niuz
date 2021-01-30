package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthorServiceTests {
    private AuthorRepository authors;
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        authors = mock(AuthorRepository.class);
        authorService = new AuthorService(authors);
    }

    @Test
    void hireAuthor() {
        authorService.hire("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 100);
        verify(authors).save(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-submission", 100));
    }
}
